package baitap06;

public class Task {
    private int id;
    private String taskName;
    private String status;

    public Task() {
    }

    public Task(int id, String taskName, String status) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void display() {
        System.out.printf("ID: %d | Công việc: %-30s | Trạng thái: %s%n",
                id, taskName, status);
    }
}
