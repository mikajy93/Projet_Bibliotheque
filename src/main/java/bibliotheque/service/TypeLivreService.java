package bibliotheque.service;

import bibliotheque.entity.TypeLivre;
import bibliotheque.repository.TypeLivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeLivreService {

    @Autowired
    private TypeLivreRepository typeLivreRepository;

    public List<TypeLivre> findAll() {
        return typeLivreRepository.findAll();
    }

    public Optional<TypeLivre> findById(int id) {
        return typeLivreRepository.findById(id);
    }

    public TypeLivre save(TypeLivre typeLivre) {
        return typeLivreRepository.save(typeLivre);
    }

    public void deleteById(int id) {
        typeLivreRepository.deleteById(id);
    }
}