package dws.test.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

public class AcceptHeaderResolverConfig extends AcceptHeaderLocaleResolver {
	
	List<Locale> LOCALES = Arrays.asList(new Locale("en", "US"));
	
	@Override
    public Locale resolveLocale(HttpServletRequest request) {
        String headerLang = request.getHeader("Accept-Language");
        System.out.println(LocaleContextHolder.getLocale());
        System.out.println(headerLang);
        return headerLang == null || headerLang.isEmpty()
                ? new Locale("en", "US")
                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }
}
