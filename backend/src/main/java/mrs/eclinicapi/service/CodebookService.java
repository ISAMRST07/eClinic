package mrs.eclinicapi.service;

import mrs.eclinicapi.repository.CodebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodebookService {

    private CodebookRepository repository;

    @Autowired
    public CodebookService(CodebookRepository repository) {
        this.repository = repository;
    }

}
