package es.uma.ingweb.rest.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Graffiti {
    @Id
    @GeneratedValue
    private Integer id;

    public Graffiti setArtist(User artist) {
        this.artist = artist;
        return this;
    }

    @ManyToOne
    private User artist;

    @Transient
    private Integer artistId;

    private double lat;
    private double lon;
    private String url;
}
