package br.com.ambientinformatica.ivolunteer.controle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ImagesView {

	private List<String> images;

	@PostConstruct
	public void init() {
		images = new ArrayList<String>();
		images.add("teste.png");
	}

	public List<String> getImages() {
		return images;
	}
}