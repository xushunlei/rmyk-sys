package rmkk.entity;

import java.util.Date;

public class Task {
    private Integer id;

    private String taskid;

    private Integer taskstate;

    private Integer tasktype;

    private String sponsorid;

    private String sealid;

    private Integer sealsnum;

    private String documentid;

    private String operatorid;

    private Date createtime;

    private Date overtime;

    private String deptopinion;

    private String dotopinion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid == null ? null : taskid.trim();
    }

    public Integer getTaskstate() {
        return taskstate;
    }

    public void setTaskstate(Integer taskstate) {
        this.taskstate = taskstate;
    }

    public Integer getTasktype() {
        return tasktype;
    }

    public void setTasktype(Integer tasktype) {
        this.tasktype = tasktype;
    }

    public String getSponsorid() {
        return sponsorid;
    }

    public void setSponsorid(String sponsorid) {
        this.sponsorid = sponsorid == null ? null : sponsorid.trim();
    }

    public String getSealid() {
        return sealid;
    }

    public void setSealid(String sealid) {
        this.sealid = sealid == null ? null : sealid.trim();
    }

    public Integer getSealsnum() {
        return sealsnum;
    }

    public void setSealsnum(Integer sealsnum) {
        this.sealsnum = sealsnum;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid == null ? null : documentid.trim();
    }

    public String getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(String operatorid) {
        this.operatorid = operatorid == null ? null : operatorid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public String getDeptopinion() {
        return deptopinion;
    }

    public void setDeptopinion(String deptopinion) {
        this.deptopinion = deptopinion == null ? null : deptopinion.trim();
    }

    public String getDotopinion() {
        return dotopinion;
    }

    public void setDotopinion(String dotopinion) {
        this.dotopinion = dotopinion == null ? null : dotopinion.trim();
    }
}