package Bootcamp6.Trello.services;

import Bootcamp6.Trello.entities.Comments;
import Bootcamp6.Trello.entities.Task;

import java.util.List;

public interface CommentService {
    Comments addComment(Comments comment);
    List<Comments> getAllComments();
    List<Comments> getCommentsTask(Task task);
}
