package rmkk.entity;

public class Dot {
    private Integer id;

    private String dotid;

    private String dotname;

    private String superdot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDotid() {
        return dotid;
    }

    public void setDotid(String dotid) {
        this.dotid = dotid == null ? null : dotid.trim();
    }

    public String getDotname() {
        return dotname;
    }

    public void setDotname(String dotname) {
        this.dotname = dotname == null ? null : dotname.trim();
    }

    public String getSuperdot() {
        return superdot;
    }

    public void setSuperdot(String superdot) {
        this.superdot = superdot == null ? null : superdot.trim();
    }
}