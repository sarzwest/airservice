package airservice.entity.bean;

/**
 * Created by papa on 3.1.15.
 */
public class FromStringBean implements Comparable {

    private String name;

    public static FromStringBean fromString(String s){
        FromStringBean f = new FromStringBean();
        f.name = s;
        return f;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FromStringBean that = (FromStringBean) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        boolean equals = equals(o);
        if(equals){
            return 0;
        }
        if(name.length() < ((FromStringBean)o).name.length()){
            return -1;
        }
        return 1;
    }
}
