package blog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NewBlogBean {
	private Kullanici kayit = new Kullanici();
	private Blog blog = new Blog();
	private Yorum yorum = new Yorum();

	EntityManager em = EntityUtil.getEntityManager();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	private boolean goster = false;

	public boolean yorumGosterButon() {
		setGoster(!isGoster());
		return isGoster();
	}

	public void yorumKaydet() {
		date = new Date();
		yorum.setKullanici(kayit);
		yorum.setBlog(blog);
		yorum.setYorumTime(dateFormat.format(date));
		em.getTransaction().begin();
		em.persist(yorum);
		em.getTransaction().commit();
		yorum = new Yorum();
		//blog = new Blog();
	}

	public String kaydet() {
		em.getTransaction().begin();
		em.persist(kayit);
		em.getTransaction().commit();
		kayit = new Kullanici();
		return "KullaniciGiris.jsf";
	}

	public void blogKaydet() {
		date = new Date();
		blog.setKullanici(kayit);
		blog.setBlogDate(dateFormat.format(date));
		em.getTransaction().begin();
		em.persist(blog);
		em.getTransaction().commit();
		blog = new Blog();
	}

	public void blogSil() {
		em.getTransaction().begin();
		em.remove(blog);
		em.getTransaction().commit();
		blog = new Blog();
	}
	
	public void yorumSil() {
		em.getTransaction().begin();
		em.remove(yorum);
		em.getTransaction().commit();
		yorum = new Yorum();
	}

	public String login() {
		for (int i = 0; i < getKullaniciKayitList().size(); i++) {
			if (getKullaniciKayitList().get(i).getE_mail().equals(kayit.getE_mail())) {
				setKayit(getKullaniciKayitList().get(i));
				return "BlogOlusturma.jsf";
			}
		}
		return "empty";
	}

	public String logout() {
		kayit = new Kullanici();
		return "KullaniciGiris.jsf";
	}

	public List<Blog> getKullaniciBlogList() {
		Query q = em.createQuery("from Blog b WHERE b.kullanici.id=:id");
		q.setParameter("id", kayit.getId());
		return q.getResultList();
	}

	public List<Blog> getBlogList() {
		return em.createQuery("from Blog").getResultList();
	}
	
	public List<Yorum> getYorumList() {
		Query q = em.createQuery("from Yorum y WHERE y.blog.blogId=:id");
		q.setParameter("id", blog.getBlogId());
		return q.getResultList();
	}

	public List<Kullanici> getKullaniciKayitList() {
		return em.createQuery("from Kullanici").getResultList();
	}

	public Kullanici getKayit() {
		return kayit;
	}

	public void setKayit(Kullanici kayit) {
		this.kayit = kayit;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public boolean isGoster() {
		return goster;
	}

	public void setGoster(boolean goster) {
		this.goster = goster;
	}

	public Yorum getYorum() {
		return yorum;
	}

	public void setYorum(Yorum yorum) {
		this.yorum = yorum;
	}
}
