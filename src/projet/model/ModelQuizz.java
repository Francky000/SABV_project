package projet.model;

import javax.inject.Inject;

import fwk3il.commun.exception.ExceptionValidation;
import fwk3il.javafx.util.UtilFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.commun.IMapper;
import projet.dao.DaoMemo;
import projet.dao.DaoPersonne;
import projet.data.Personne;
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
    private DaoQuestion			daoQuestion;
    
	
	
	// Getters 
	
	public ObservableList<Quizz> getListeQuizz() {
		return liste;
	}
	
	public Quizz getEnCoursQuizz() {
		return enCoursQuizz;
	}
	
	public Question getEnCoursQues() {
		return enCoursQues;
	}

	
	// Actualisations
	
	public void actualiserListe() {
		listeQuizz.clear();
		listeQuizz.addAll( daoQuizz.listerTout() );
		listeQues.clear();
		listeQues.addAll( daoQues.listerTout() );
	}
	
}
