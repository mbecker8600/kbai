package ravensproject.util;

import ravensproject.RavensFigure;
import ravensproject.RavensProblem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by michaelbecker on 6/24/16.
 */
public class ImageUtil {

    private static final int black = 0xFF000000;
    private static final int white = 0xFFFFFFFF;

    public static final double COMPARE_THRESHOLD = .96;

    /**
     * Provides a comparison between the first image given and the second image given. This is given as a percentage
     * since not all images will be completely alike.
     *
     * @param one - The first image
     * @param two - The second image
     * @return double - The percentage of matches to the total pixels in the first image
     */
    public static double compareImages(BufferedImage one, BufferedImage two){
        double matches = 0;
        double total = 0;
        if(one != null && two != null){
            int width = one.getWidth();
            int height = one.getHeight();
            total = width * height;
            for(int i = 0; i < width; i++)
            {
                for(int j=0; j < height; j++)
                {
                    if (one.getRGB(i, j) == two.getRGB(i, j))
                    {
                        matches++;
                    }
                }
            }
        }
        return matches/total;
    }

    public static boolean imagesEqual(BufferedImage one, BufferedImage two){
        boolean equal = false;
        double comparision = compareImages(one, two);
        if(comparision >= COMPARE_THRESHOLD) equal = true;
        return equal;
    }

    /**
     * Returns either a 2x2 array or a 3x3 array depending on the type of problem. It will consist of the buffered images
     * of each of the problems
     *
     * @param problem
     * @return
     */
    public static BufferedImage[][] getFrameImages(RavensProblem problem){
        BufferedImage[][] frameImages = new BufferedImage[3][3];

        if(problem.getProblemType().equals("2x2")){
            try{
                BufferedImage imageA = ImageIO.read(new File(problem.getFigures().get(String.valueOf("A")).getVisual()));
                normalizeImage(imageA);
                frameImages[0][0] = imageA;

                BufferedImage imageB = ImageIO.read(new File(problem.getFigures().get(String.valueOf("B")).getVisual()));
                normalizeImage(imageB);
                frameImages[1][0] = imageB;

                BufferedImage imageC = ImageIO.read(new File(problem.getFigures().get(String.valueOf("C")).getVisual()));
                normalizeImage(imageC);
                frameImages[0][1] = imageC;
            }
            catch(Exception e){

            }
        }
        else if(problem.getProblemType().equals("3x3")){
            try{
                BufferedImage imageA = ImageIO.read(new File(problem.getFigures().get(String.valueOf("A")).getVisual()));
                normalizeImage(imageA);
                frameImages[0][0] = imageA;

                BufferedImage imageB = ImageIO.read(new File(problem.getFigures().get(String.valueOf("B")).getVisual()));
                normalizeImage(imageB);
                frameImages[1][0] = imageB;

                BufferedImage imageC = ImageIO.read(new File(problem.getFigures().get(String.valueOf("C")).getVisual()));
                normalizeImage(imageC);
                frameImages[2][0] = imageC;

                BufferedImage imageD = ImageIO.read(new File(problem.getFigures().get(String.valueOf("D")).getVisual()));
                normalizeImage(imageD);
                frameImages[0][1] = imageD;

                BufferedImage imageE = ImageIO.read(new File(problem.getFigures().get(String.valueOf("E")).getVisual()));
                normalizeImage(imageE);
                frameImages[1][1] = imageE;

                BufferedImage imageF = ImageIO.read(new File(problem.getFigures().get(String.valueOf("F")).getVisual()));
                normalizeImage(imageF);
                frameImages[2][1] = imageF;

                BufferedImage imageG = ImageIO.read(new File(problem.getFigures().get(String.valueOf("G")).getVisual()));
                normalizeImage(imageG);
                frameImages[0][2] = imageG;

                BufferedImage imageH = ImageIO.read(new File(problem.getFigures().get(String.valueOf("H")).getVisual()));
                normalizeImage(imageH);
                frameImages[1][2] = imageH;
            }
            catch(Exception e){

            }
        }

        return frameImages;
    }

    public static List<BufferedImage> getAnswerImages(RavensProblem problem){
       List<BufferedImage> imageList = new ArrayList<BufferedImage>();

        for(int i=1; i<9; i++){
            try {
                RavensFigure figure = problem.getFigures().get(String.valueOf(i));
                if(figure != null){
                    BufferedImage image = ImageIO.read(new File(figure.getVisual()));
                    imageList.add(image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imageList;
    }

    public static void normalizeImage(BufferedImage image)
    {
        int height = image.getHeight();
        int width = image.getWidth();

        for(int i=0, j=0; i<width; i++) {
            for(j=0; j<height; j++) {
                int rgb = image.getRGB(i, j);
                if (rgb != white && rgb != black)
                {
                    image.setRGB(i, j, white);
                }
            }
        }
    }

    public static BufferedImage differences(BufferedImage one, BufferedImage two){

        int width = Math.min(one.getWidth(), two.getWidth());
        int height = Math.min(one.getHeight(), two.getHeight());
        BufferedImage differences = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                if(!pixelCompare(one, two, x, y)){
                    differences.setRGB(x, y, black);
                }
            }
        }

        return differences;
    }

    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static int figureArea(BufferedImage image){

        int width_start = -1, width_end = -1, height_start = -1, height_end = -1;

        for(int x=0;x<image.getWidth(); x++){
            int localHeightStart = -1, localHeightEnd = -1;
            for(int y=0;y<image.getHeight(); y++){
                if(image.getRGB(x,y) == black && localHeightStart < 0){
                    localHeightStart = y;
                }
                else if(image.getRGB(x,y) == black && localHeightStart > 0){
                    localHeightEnd = y;
                }
            }
            if(height_start < 0) height_start = localHeightStart;
            else if(localHeightStart > 0 && localHeightEnd < height_start) height_start = localHeightStart;

            if(height_end < 0) height_end = localHeightEnd;
            else if(localHeightEnd > 0 && localHeightEnd > height_end) height_end = localHeightEnd;

            if(localHeightStart > 0 && localHeightEnd > 0 && width_start < 0){
                width_start = x;
            }
            else if(localHeightStart > 0 && localHeightEnd > 0 && width_start > 0){
                width_end = x;
            }
        }

        int total = 0;
        if(width_start > 0 && width_end > 0 && height_start > 0 && height_end > 0){
            total = (width_end - width_start) * (height_end - height_start);
        }
        return total;
    }

    private static boolean pixelCompare(BufferedImage one, BufferedImage two, int x, int y){
        boolean same = false;

        int height = Math.min(one.getHeight(), two.getHeight());
        int width = Math.min(one.getWidth(), two.getWidth());
        for(int i = Math.max(x-2, 0); i<Math.min(x+3, width-1); i++) {
            for(int j = Math.max(y-2, 0); j<Math.min(y+3, height-1); j++) {
                if (one.getRGB(x, y) == two.getRGB(i, j)) {
                    same = true;
                    break;
                }
                if (same) {
                    break;
                }
            }
        }

        return same;
    }

}
