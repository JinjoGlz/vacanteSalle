import org.springframework.web.servlet.i18n.FixedLocaleResolver

beans = {
    localeResolver(FixedLocaleResolver, new Locale('es'))
}
