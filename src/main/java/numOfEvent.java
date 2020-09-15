import java.io.Serializable;

public class numOfEvent implements Serializable {
    int PushEvent = 0;
    int IssueCommentEvent = 0;
    int IssuesEvent = 0;
    int PullRequestEvent = 0;

    public int getPushEvent() {
        return PushEvent;
    }

    public int getIssueCommentEvent() {
        return IssueCommentEvent;
    }

    public int getIssuesEvent() {
        return IssuesEvent;
    }

    public int getPullRequestEvent() {
        return PullRequestEvent;
    }

    public void setPushEvent(int pushEvent) {
        PushEvent = pushEvent;
    }

    public void setIssueCommentEvent(int issueCommentEvent) {
        IssueCommentEvent = issueCommentEvent;
    }

    public void setIssuesEvent(int issuesEvent) {
        IssuesEvent = issuesEvent;
    }

    public void setPullRequestEvent(int pullRequestEvent) {
        PullRequestEvent = pullRequestEvent;
    }

    //    @Override
//    public String toString() {
//        return "event:{" +
//                "PushEvent=" + PushEvent +
//                ", IssueCommentEvent=" + IssueCommentEvent +
//                ", IssuesEvent=" + IssuesEvent +
//                ", PullRequestEvent=" + PullRequestEvent +
//                '}';
//    }
}
