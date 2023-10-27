/*


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired


    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialRepository
                    .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
package com.bezkoder.spring.hibernate.onetomany.controller;

        import java.util.ArrayList;
        import java.util.List;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
        import com.bezkoder.spring.hibernate.onetomany.model.Tutorial;
        import com.bezkoder.spring.hibernate.onetomany.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
        return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _tutorial.setTitle(tutorial.getTitle());
        _tutorial.setDescription(tutorial.getDescription());
        _tutorial.setPublished(tutorial.isPublished());

        return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        tutorialRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }
}

package com.bezkoder.spring.hibernate.onetomany.controller;

        import java.util.List;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.DeleteMapping;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.PutMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import com.bezkoder.spring.hibernate.onetomany.exception.ResourceNotFoundException;
        import com.bezkoder.spring.hibernate.onetomany.model.Comment;
        import com.bezkoder.spring.hibernate.onetomany.repository.CommentRepository;
        import com.bezkoder.spring.hibernate.onetomany.repository.TutorialRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        List<Comment> comments = commentRepository.findByTutorialId(tutorialId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                 @RequestBody Comment commentRequest) {
        Comment comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") long id, @RequestBody Comment commentRequest) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));

        comment.setContent(commentRequest.getContent());

        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        commentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        commentRepository.deleteByTutorialId(tutorialId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.bezkoder.spring.hibernate.manytomany.model;

@Entity
@Table(name = "tutorials")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,  CascadeType.MERGE })
    @JoinTable(name = "tutorial_tags",
            joinColumns = { @JoinColumn(name = "tutorial_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tag> tags = new HashSet<>();

    // getters and setters

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getTutorials().add(this);
    }

    public void removeTag(long tagId) {
        Tag tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
        if (tag != null) {
            this.tags.remove(tag);
            tag.getTutorials().remove(this);
        }
    }
}

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    @JsonIgnore
    private Set<Tutorial> tutorials = new HashSet<>();

    public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
        // ...
        List<Tutorial> findTutorialsByTagsId(Long tagId);
    }

    public interface TagRepository extends JpaRepository<Tag, Long> {
        List<Tag> findTagsByTutorialsId(Long tutorialId);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RestController
    @RequestMapping("/api")
    public class TutorialController {

        @Autowired
        TutorialRepository tutorialRepository;

        @GetMapping("/tutorials")
        public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }

        @GetMapping("/tutorials/{id}")
        public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
            Tutorial tutorial = tutorialRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        }

        @PostMapping("/tutorials")
        public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
            Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        }

        @PutMapping("/tutorials/{id}")
        public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
            Tutorial _tutorial = tutorialRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());

            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        }

        @DeleteMapping("/tutorials/{id}")
        public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
            tutorialRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/tutorials")
        public ResponseEntity<HttpStatus> deleteAllTutorials() {
            tutorialRepository.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/tutorials/published")
        public ResponseEntity<List<Tutorial>> findByPublished() {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @RestController
    @RequestMapping("/api")
    public class TagController {

        @Autowired
        private TutorialRepository tutorialRepository;

        @Autowired
        private TagRepository tagRepository;

        @GetMapping("/tags")
        public ResponseEntity<List<Tag>> getAllTags() {
            List<Tag> tags = new ArrayList<Tag>();

            tagRepository.findAll().forEach(tags::add);

            if (tags.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tags, HttpStatus.OK);
        }

        @GetMapping("/tutorials/{tutorialId}/tags")
        public ResponseEntity<List<Tag>> getAllTagsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
            if (!tutorialRepository.existsById(tutorialId)) {
                throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
            }

            List<Tag> tags = tagRepository.findTagsByTutorialsId(tutorialId);
            return new ResponseEntity<>(tags, HttpStatus.OK);
        }

        @GetMapping("/tags/{id}")
        public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Long id) {
            Tag tag = tagRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));

            return new ResponseEntity<>(tag, HttpStatus.OK);
        }

        @GetMapping("/tags/{tagId}/tutorials")
        public ResponseEntity<List<Tutorial>> getAllTutorialsByTagId(@PathVariable(value = "tagId") Long tagId) {
            if (!tagRepository.existsById(tagId)) {
                throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
            }

            List<Tutorial> tutorials = tutorialRepository.findTutorialsByTagsId(tagId);
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }

        @PostMapping("/tutorials/{tutorialId}/tags")
        public ResponseEntity<Tag> addTag(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Tag tagRequest) {
            Tag tag = tutorialRepository.findById(tutorialId).map(tutorial -> {
                long tagId = tagRequest.getId();

                // tag is existed
                if (tagId != 0L) {
                    Tag _tag = tagRepository.findById(tagId)
                            .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
                    tutorial.addTag(_tag);
                    tutorialRepository.save(tutorial);
                    return _tag;
                }

                // add and create new Tag
                tutorial.addTag(tagRequest);
                return tagRepository.save(tagRequest);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

            return new ResponseEntity<>(tag, HttpStatus.CREATED);
        }

        @PutMapping("/tags/{id}")
        public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
            Tag tag = tagRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

            tag.setName(tagRequest.getName());

            return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.OK);
        }

        @DeleteMapping("/tutorials/{tutorialId}/tags/{tagId}")
        public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "tutorialId") Long tutorialId, @PathVariable(value = "tagId") Long tagId) {
            Tutorial tutorial = tutorialRepository.findById(tutorialId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

            tutorial.removeTag(tagId);
            tutorialRepository.save(tutorial);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/tags/{id}")
        public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
            tagRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @Entity
    @Table(name = "tutorials")
    public class Tutorial {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "title")
        private String title;

        @Column(name = "description")
        private String description;

        @Column(name = "published")
        private boolean published;
    }
    @Entity
    @Table(name = "tutorial_details")
    public class TutorialDetails {
        @Id
        private Long id;

        @Column
        private Date createdOn;

        @Column
        private String createdBy;

        @OneToOne(fetch = FetchType.LAZY)
        @MapsId
        @JoinColumn(name = "tutorial_id")
        private Tutorial tutorial;
    }
    @Repository
    public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
        List<Tutorial> findByPublished(boolean published);

        List<Tutorial> findByTitleContaining(String title);
    }
    @Repository
    public interface TutorialDetailsRepository extends JpaRepository<TutorialDetails, Long> {
        @Transactional
        void deleteById(long id);

        @Transactional
        void deleteByTutorialId(long tutorialId);
    }

    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/api")
    public class TutorialController {

        @Autowired
        TutorialRepository tutorialRepository;

        @Autowired
        private TutorialDetailsRepository detailsRepository;

        @GetMapping("/tutorials")
        public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }

        @GetMapping("/tutorials/{id}")
        public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
            Tutorial tutorial = tutorialRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        }

        @PostMapping("/tutorials")
        public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
            Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        }

        @PutMapping("/tutorials/{id}")
        public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
            Tutorial _tutorial = tutorialRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());

            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        }

        @DeleteMapping("/tutorials/{id}")
        public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
            if (detailsRepository.existsById(id)) {
                detailsRepository.deleteById(id);
            }

            tutorialRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/tutorials")
        public ResponseEntity<HttpStatus> deleteAllTutorials() {
            tutorialRepository.deleteAll();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/tutorials/published")
        public ResponseEntity<List<Tutorial>> findByPublished() {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "*")
    @RestController
    @RequestMapping("/api")
    public class TutorialDetailsController {
        @Autowired
        private TutorialDetailsRepository detailsRepository;

        @Autowired
        private TutorialRepository tutorialRepository;

        @GetMapping({ "/details/{id}", "/tutorials/{id}/details" })
        public ResponseEntity<TutorialDetails> getDetailsById(@PathVariable(value = "id") Long id) {
            TutorialDetails details = detailsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial Details with id = " + id));

            return new ResponseEntity<>(details, HttpStatus.OK);
        }

        @PostMapping("/tutorials/{tutorialId}/details")
        public ResponseEntity<TutorialDetails> createDetails(@PathVariable(value = "tutorialId") Long tutorialId,
                                                             @RequestBody TutorialDetails detailsRequest) {
            Tutorial tutorial = tutorialRepository.findById(tutorialId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

            detailsRequest.setCreatedOn(new java.util.Date());
            detailsRequest.setTutorial(tutorial);
            TutorialDetails details = detailsRepository.save(detailsRequest);

            return new ResponseEntity<>(details, HttpStatus.CREATED);
        }

        @PutMapping("/details/{id}")
        public ResponseEntity<TutorialDetails> updateDetails(@PathVariable("id") long id,
                                                             @RequestBody TutorialDetails detailsRequest) {
            TutorialDetails details = detailsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Id " + id + " not found"));

            details.setCreatedBy(detailsRequest.getCreatedBy());

            return new ResponseEntity<>(detailsRepository.save(details), HttpStatus.OK);
        }

        @DeleteMapping("/details/{id}")
        public ResponseEntity<HttpStatus> deleteDetails(@PathVariable("id") long id) {
            detailsRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @DeleteMapping("/tutorials/{tutorialId}/details")
        public ResponseEntity<TutorialDetails> deleteDetailsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
            if (!tutorialRepository.existsById(tutorialId)) {
                throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
            }

            detailsRepository.deleteByTutorialId(tutorialId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // OneToMany
    @Entity
    @Table(name = "posts")
    public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "title")
        private String title;

        @Column(name = "description")
        private String description;

        @OneToMany(cascade = CascadeType.ALL)
        @JoinColumn(name = "pc_fid", referencedColumnName = "id")
        List<Comment> comments = new ArrayList<>();
    }
    @Entity
    @Table(name = "comments")
    public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String text;
    }*/
