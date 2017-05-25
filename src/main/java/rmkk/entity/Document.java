package rmkk.entity;

import java.util.Date;

public class Document {
    private Integer id;

    private String documentid;

    private String documentname;
    
    private Integer istemplate;

    private String sealid;
    
    private Integer needride;
    
    private String rideid;

    public Integer getIstemplate()
	{
		return istemplate;
	}

	public void setIstemplate(Integer istemplate)
	{
		this.istemplate = istemplate;
	}

	public Integer getNeedride()
	{
		return needride;
	}

	public void setNeedride(Integer needride)
	{
		this.needride = needride;
	}

	public String getRideid()
	{
		return rideid;
	}

	public void setRideid(String rideid)
	{
		this.rideid = rideid;
	}

	private Integer documentstate;

    private String documenturl;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid == null ? null : documentid.trim();
    }

    public String getDocumentname() {
        return documentname;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname == null ? null : documentname.trim();
    }

    public String getSealid() {
        return sealid;
    }

    public void setSealid(String sealid) {
        this.sealid = sealid == null ? null : sealid.trim();
    }

    public Integer getDocumentstate() {
        return documentstate;
    }

    public void setDocumentstate(Integer documentstate) {
        this.documentstate = documentstate;
    }

    public String getDocumenturl() {
        return documenturl;
    }

    public void setDocumenturl(String documenturl) {
        this.documenturl = documenturl == null ? null : documenturl.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}