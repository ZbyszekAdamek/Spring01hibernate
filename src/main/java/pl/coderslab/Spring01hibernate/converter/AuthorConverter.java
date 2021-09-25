package pl.coderslab.Spring01hibernate.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.Spring01hibernate.dao.AuthorDao;
import pl.coderslab.Spring01hibernate.entity.Author;

@Component
public class AuthorConverter implements Converter<String, Author> {

    @Autowired
    private AuthorDao authorDao;

    @Override
    public Author convert(String s) {
        return authorDao.readAuthor(Long.parseLong(s));
    }
}
