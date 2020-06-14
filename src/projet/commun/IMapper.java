package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Diplome;
import projet.data.Memo;
import projet.data.Personne;
import projet.data.Question;
import projet.data.Quizz;
import projet.data.Reponse;
import projet.data.Theme;
import projet.data.Visiteur;

@Mapper
public interface IMapper {

	Theme     update(@MappingTarget Theme enCours, Theme theme);

	Categorie update(@MappingTarget Categorie target, Categorie source);

	Visiteur update(@MappingTarget Visiteur target, Visiteur source);

	Question update(@MappingTarget Question target, Question source);

	Reponse update(@MappingTarget Reponse target, Reponse source);
	
	Quizz update(@MappingTarget Quizz target,Quizz source);
	
	Diplome update(@MappingTarget Diplome target,Diplome source);
}
