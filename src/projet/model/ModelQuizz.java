package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoQuestion;
import projet.dao.DaoQuizz;
import projet.dao.DaoTheme;
import projet.data.Personne;
import projet.data.Question;
import projet.data.Quizz;
import projet.data.Telephone;


public class ModelQuizz {
	
	
	// Données observables 
	
	private final ObservableList<Quizz> listeQuizz = FXCollections.observableArrayList();
	private final ObservableList<Question> listeQues = FXCollections.observableArrayList();
	 
	private final Quizz		enCoursQuizz = new Quizz();
	private final Question		enCoursQues = new Question();
	
	
	// Autres champs
	
    @Inject
	private IMapper		        mapper;
    @Inject
	private DaoQuizz			daoQuizz;
    @Inject
    private DaoTheme			daoTheme;
    @Inject
    private DaoQuestion			daoQues;
    
	
	
	// Getters 
	
    public ObservableList<Quizz> getListeQuizz() {
		return listeQuizz;
	}
    
    public ObservableList<Question> getListeQues() {
		return listeQues;
	}
	
	public Quizz getEnCoursQuizz() {
		return enCoursQuizz;
	}
	
	public Question getEnCoursQues() {
		return enCoursQues;
	}
	

	
	// Actualisations
	
//	public void actualiserListe() {
//		listeQuizz.clear();
//		listeQuizz.addAll( daoQuizz.ListQuizz());
//		listeQues.clear();
//		listeQues.addAll( daoQues.listReponse(getEnCoursQues()) );
//	}
	
}
