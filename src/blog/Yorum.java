package blog;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Yorum implements Serializable {

	@Id
	@GeneratedValue
	private Integer yorumId;
	private String yorumText;
	private String yorumTime;

	@JoinColumn(name = "id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Kullanici kullanici;
	@JoinColumn(name = "blogId", referencedColumnName = "blogId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Blog blog;

	public Integer getId() {
		return yorumId;
	}

	public void setId(Integer id) {
		this.yorumId = id;
	}

	public String getYorumText() {
		return yorumText;
	}

	public void setYorumText(String yorumText) {
		this.yorumText = yorumText;
	}

	public String getYorumTime() {
		return yorumTime;
	}

	public void setYorumTime(String yorumTime) {
		this.yorumTime = yorumTime;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

}
