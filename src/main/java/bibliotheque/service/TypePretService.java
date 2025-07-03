package bibliotheque.service;

import bibliotheque.entity.TypePret;
import bibliotheque.repository.TypePretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypePretService {

    @Autowired
    private TypePretRepository typePretRepository;

    public List<TypePret> findAll() {
        return typePretRepository.findAll();
    }

    public Optional<TypePret> findById(int id) {
        return typePretRepository.findById(id);
    }

    public TypePret save(TypePret typePret) {
        return typePretRepository.save(typePret);
    }

    public void deleteById(int id) {
        typePretRepository.deleteById(id);
    }
}