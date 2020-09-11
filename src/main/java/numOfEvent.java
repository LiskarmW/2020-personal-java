public class numOfEvent {
    int PushEvent = 0;
    int IssueCommentEvent = 0;
    int IssuesEvent = 0;
    int PullRequestEvent = 0;

    @Override
    public String toString() {
        return "event:{" +
                "PushEvent=" + PushEvent +
                ", IssueCommentEvent=" + IssueCommentEvent +
                ", IssuesEvent=" + IssuesEvent +
                ", PullRequestEvent=" + PullRequestEvent +
                '}';
    }
}
