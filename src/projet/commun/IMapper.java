package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Memo;
import projet.data.Personne;
import projet.data.Theme;
import projet.data.Visiteur;


@Mapper
public interface IMapper {  
	
	Theme update( @MappingTarget Theme enCours, Theme theme  );      
	
	Categorie update( @MappingTarget Categorie target, Categorie source );

	Visiteur update( @MappingTarget Visiteur target, Visiteur source );	
}
