package xmlweb.projekat.model.dtos;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola
 *
 */
public class CommentDTO {

	
	private long id;
	@NotNull
	private String content;
	@NotNull
	private int rating;
	
	private Boolean approved;
	@NotNull
	private long accomodation;
	@NotNull
	private int version;
	@NotNull
	private long author;
	
	private String authorName;
	private String accomodationName;
	
	public CommentDTO(){
		
	}
	public CommentDTO(long id, @NotNull String content, @NotNull int rating, Boolean approved,
			@NotNull long accomodation, @NotNull int version, @NotNull long author) {
		super();
		this.id = id;
		this.content = content;
		this.rating = rating;
		this.approved = approved;
		this.accomodation = accomodation;
		this.version = version;
		this.author = author;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public long getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(long accomodation) {
		this.accomodation = accomodation;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getAccomodationName() {
		return accomodationName;
	}

	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
