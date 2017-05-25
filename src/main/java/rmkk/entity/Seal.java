package rmkk.entity;

public class Seal {
    private Integer id;

    private String sealid;

    private String sealname;

    private String sealtype;

    private String sealimg;

    private Integer sealstate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSealid() {
        return sealid;
    }

    public void setSealid(String sealid) {
        this.sealid = sealid == null ? null : sealid.trim();
    }

    public String getSealname() {
        return sealname;
    }

    public void setSealname(String sealname) {
        this.sealname = sealname == null ? null : sealname.trim();
    }

    public String getSealtype() {
        return sealtype;
    }

    public void setSealtype(String sealtype) {
        this.sealtype = sealtype == null ? null : sealtype.trim();
    }

    public String getSealimg() {
        return sealimg;
    }

    public void setSealimg(String sealimg) {
        this.sealimg = sealimg == null ? null : sealimg.trim();
    }

    public Integer getSealstate() {
        return sealstate;
    }

    public void setSealstate(Integer sealstate) {
        this.sealstate = sealstate;
    }
}