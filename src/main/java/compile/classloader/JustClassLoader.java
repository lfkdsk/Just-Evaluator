package compile.classloader;

import javax.tools.FileObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liufengkai on 2017/7/20.
 */
public class JustClassLoader extends ClassLoader {

    private final Map<String, FileObject> compileFileMap = new HashMap<>();

    public JustClassLoader(ClassLoader parent) {
        super(parent);
    }

    public FileObject addClass(String qualifiedClassName, final FileObject javaFile) {
        return compileFileMap.put(qualifiedClassName, javaFile);
    }

    @Override
    protected Class<?> findClass(String qualifiedClassName) throws ClassNotFoundException {
        FileObject file = compileFileMap.remove(qualifiedClassName);
        if (file != null) {
            // TODO
//            byte[] bytes = ((FelJavaFileObject) file).getByteCode();
//            return defineClass(qualifiedClassName, bytes, 0, bytes.length);
        }
        return super.findClass(qualifiedClassName);
    }
}
