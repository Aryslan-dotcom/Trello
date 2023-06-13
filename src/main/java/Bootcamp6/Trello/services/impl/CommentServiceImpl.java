package Bootcamp6.Trello.services.impl;

import Bootcamp6.Trello.entities.Comments;
import Bootcamp6.Trello.entities.Task;
import Bootcamp6.Trello.repositories.CommentsRepositories;
import Bootcamp6.Trello.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    CommentsRepositories commentsRepositories;
    @Override
    public Comments addComment(Comments comment) {
        return commentsRepositories.save(comment);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentsRepositories.findAll();
    }

    @Override
    public List<Comments> getCommentsTask(Task task) {
        List<Comments> comments = commentsRepositories.findAll();
        List<Comments> newcoments = new ArrayList<>();
        for (int i = 0; i<comments.size();i++){
            if (comments.get(i).getTask().getId() == task.getId()){
                newcoments.add(comments.get(i));
            }
        }
        return newcoments;
    }
}
