package ravensproject.util;

/**
 * Created by michaelbecker on 7/3/16.
 */
public enum SizeEnum {
    VERY_SMALL ("very small"),
    SMALL ("small"),
    MEDIUM ("medium"),
    LARGE ("large"),
    VERY_LARGE ("very large"),
    HUGE ("huge");

    private static SizeEnum[] vals = values();
    private final String name;

    SizeEnum(String size){
        name = size;
    }

    public SizeEnum next() {
        if(this.ordinal()+1 < vals.length) return vals[(this.ordinal()+1)];
        return null;
    }

    public SizeEnum previous()
    {
        if(this.ordinal() > 0) return vals[(this.ordinal()-1)];
        return null;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

    public static SizeEnum fromString(String text) {
        if (text != null) {
            for (SizeEnum b : SizeEnum.values()) {
                if (text.equalsIgnoreCase(b.name)) {
                    return b;
                }
            }
        }
        return null;
    }
}
