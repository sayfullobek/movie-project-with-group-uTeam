package Movie.Movie.Repository;

import Movie.Movie.Entity.template.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Attachment findAttachmentById(UUID id);
}
