package projet.commun;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.data.Categorie;
import projet.data.Compte;
import projet.data.Memo;
import projet.data.Personne;


@Mapper
public interface IMapper {  
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
	Categorie update( @MappingTarget Categorie target, Categorie source );

	Personne update( @MappingTarget Personne target, Personne source );

	Memo update( @MappingTarget Memo target, Memo source );
	
}
