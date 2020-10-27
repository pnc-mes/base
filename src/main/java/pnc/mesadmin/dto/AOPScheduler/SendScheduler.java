package pnc.mesadmin.dto.AOPScheduler;

import java.util.List;

/**
 * Created by PNC on 2019/5/13.
 */
public class SendScheduler {
    private String  taskType;
    private String  taskStatus;
    private String  objType;
    private String  objTag;
    private String  objTagName;
    private int  objRd;
    private String  lastTaskRdList;
    private List <TaskList> taskListList;

  public static class  TaskList{
       private int  taskRd;
       private float  lowerTimeValue;
       private float  superTimeValue;
       private float  tqTimeValue;
       // 00#每天指定时间运行  01#每天间隔时间运行 02#每周运行 03#每月运行 04#每季度运行   05#每年 06#指定日期
       private String periodType;
       private  Tqrevice tqrevice;
       private  Gqrevice gqrevice;
       private Dqrevice dqrevice;

      public int getTaskRd() {
          return taskRd;
      }

      public void setTaskRd(int taskRd) {
          this.taskRd = taskRd;
      }

      public float getLowerTimeValue() {
          return lowerTimeValue;
      }

      public void setLowerTimeValue(float lowerTimeValue) {
          this.lowerTimeValue = lowerTimeValue;
      }

      public float getSuperTimeValue() {
          return superTimeValue;
      }

      public void setSuperTimeValue(float superTimeValue) {
          this.superTimeValue = superTimeValue;
      }

      public float getTqTimeValue() {
          return tqTimeValue;
      }

      public void setTqTimeValue(float tqTimeValue) {
          this.tqTimeValue = tqTimeValue;
      }

      public Tqrevice getTqrevice() {
          return tqrevice;
      }

      public void setTqrevice(Tqrevice tqrevice) {
          this.tqrevice = tqrevice;
      }

      public Gqrevice getGqrevice() {
          return gqrevice;
      }

      public void setGqrevice(Gqrevice gqrevice) {
          this.gqrevice = gqrevice;
      }

      public Dqrevice getDqrevice() {
          return dqrevice;
      }

      public void setDqrevice(Dqrevice dqrevice) {
          this.dqrevice = dqrevice;
      }

      public String getPeriodType() {
          return periodType;
      }

      public void setPeriodType(String periodType) {
          this.periodType = periodType;
      }
  }



    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjTag() {
        return objTag;
    }

    public void setObjTag(String objTag) {
        this.objTag = objTag;
    }

    public String getObjTagName() {
        return objTagName;
    }

    public void setObjTagName(String objTagName) {
        this.objTagName = objTagName;
    }

    public int getObjRd() {
        return objRd;
    }

    public void setObjRd(int objRd) {
        this.objRd = objRd;
    }

    public String getLastTaskRdList() {
        return lastTaskRdList;
    }

    public void setLastTaskRdList(String lastTaskRdList) {
        this.lastTaskRdList = lastTaskRdList;
    }

    public List<TaskList> getTaskListList() {
        return taskListList;
    }

    public void setTaskListList(List<TaskList> taskListList) {
        this.taskListList = taskListList;
    }
}
