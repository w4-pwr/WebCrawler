package pwr.po.webcrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pwr.po.webcrawler.repository.PreferencesRepository;

@Service
public class PreferencesService {

    @Autowired
    private PreferencesRepository preferencesRepository;
}
