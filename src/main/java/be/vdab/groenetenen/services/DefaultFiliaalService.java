package be.vdab.groenetenen.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.repositories.FiliaalRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultFiliaalService implements FiliaalService{

	private final FiliaalRepository filiaalRepository;
	
	DefaultFiliaalService(FiliaalRepository filiaalRepository) {
		this.filiaalRepository = filiaalRepository;
	}

	@Override
	public List<Filiaal> findByPostcode(int van, int tot) {
		return this.filiaalRepository.findByAdresPostcodeBetweenOrderByAdresPostcode(van, tot);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void delete(long id) {
		Optional<Filiaal> optionalFiliaal = this.filiaalRepository.findById(id);
		if(optionalFiliaal.isPresent()) {
			if(! optionalFiliaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			this.filiaalRepository.deleteById(optionalFiliaal.get().getId());
		}
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(Filiaal filiaal) {
		this.filiaalRepository.save(filiaal);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Filiaal filiaal) {
		this.filiaalRepository.save(filiaal);
	}

	@Override
	public Optional<Filiaal> findById(long id) {
		return this.filiaalRepository.findById(id);
	}
	
	
}
