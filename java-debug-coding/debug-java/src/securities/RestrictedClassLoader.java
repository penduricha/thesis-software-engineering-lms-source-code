package securities;


import java.net.URL;
import java.net.URLClassLoader;

public class RestrictedClassLoader extends URLClassLoader {
    public RestrictedClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (name.startsWith("java.util.") || name.startsWith("java.lang.") || name.startsWith("java.time.")) {
            return super.loadClass(name, resolve);
        }
        throw new ClassNotFoundException("Access denied to class: " + name);
    }
}