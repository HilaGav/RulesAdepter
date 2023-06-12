package tech.adept.rules.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.adept.rules.model.Rule;
import tech.adept.rules.repository.RuleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final RuleRepository ruleRepository;

    public RuleController(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Rule>> getAllRules() {
        List<Rule> tutorials = ruleRepository.findAll();
        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rule> getRuleById(@PathVariable("id") long id) {
        return ruleRepository.findById(id)
                .map(rule -> new ResponseEntity<>(rule, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        try {
            Rule _rule = ruleRepository.save(new Rule(rule.getId(), rule.getTitle(), rule.getDescription(),
                    false, rule.getCondition(), rule.getCountries(), rule.getAction()));
            return new ResponseEntity<>(_rule, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rule> updateRule(@PathVariable("id") long id, @RequestBody Rule rule) {
        Optional<Rule> tutorialData = ruleRepository.findById(id);

        if (tutorialData.isPresent()) {
            Rule _rule = tutorialData.get();
            _rule.setTitle(rule.getTitle());
            _rule.setDescription(rule.getDescription());
            _rule.setPublished(rule.isPublished());
            return new ResponseEntity<>(ruleRepository.save(_rule), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRule(@PathVariable("id") long id) {
        ruleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllRules() {
        ruleRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
