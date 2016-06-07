package blog;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.Pattern;
import org.hibernate.validator.Patterns;

@Entity
@Table(name = "kullanicilar")
public class Kullanici implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private String ad;
	private String soyad;
	private String yas;
	private String sifre;
	@Column(name = "email")
	@Pattern(regex = ".+@.+\\.[a-z]+", message = "Geçersiz e-mail girdiniz.")
	private String e_mail;
	
	@OneToMany(mappedBy = "kullanici", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Yorum> yorum;
	@OneToMany(mappedBy = "kullanici", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Blog> blog;

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getYas() {
		return yas;
	}

	public void setYas(String yas) {
		this.yas = yas;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public List<Blog> getBlog() {
		return blog;
	}

	public void setBlog(List<Blog> blog) {
		this.blog = blog;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Yorum> getYorum() {
		return yorum;
	}

	public void setYorum(List<Yorum> yorum) {
		this.yorum = yorum;
	}
}
