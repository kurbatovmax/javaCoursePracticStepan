package im.kmg.sibniar2;

import im.kmg.sibniar2.commands.CommandDataContainer;
import im.kmg.sibniar2.commands.ICommand;
import im.kmg.sibniar2.commands.KMGResource;


import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Singleton
 * Created with IntelliJ IDEA.
 * User: maxim kurbatov
 * Date: 9/13/13
 * Time: 8:39 PM
 */
public class CommandPluginLoader
{
    static private CommandPluginLoader m_this;
    private CommandDataContainer m_data;

    /**
     *
     */
    private CommandPluginLoader() {
        CommandPluginLoader.m_this = this;
        m_data = new CommandDataContainer(new ArrayList<ICommand>(), new KMGStack());
    }

    /**
     *
     * @return
     */
    public static CommandPluginLoader Instance() {
        if (CommandPluginLoader.m_this == null) {
            CommandPluginLoader.m_this = new CommandPluginLoader();
        }
        return CommandPluginLoader.m_this;
    }

    /**
     *
     * @param fileName
     */
    public void reload(String fileName) throws
            IOException,
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            ClassNotFoundException,
            ExceptionNoPlugins
    {
        InputStream in =  Class.class.getResourceAsStream(fileName);
        Properties properties = new Properties();
        properties.load(in);

        Set<?> s =  properties.keySet();

        List<Integer> s1 = new ArrayList<>();

        for(Object item : s) {
            s1.add(Integer.valueOf((String)item));
        }

        Collections. sort(s1);


        for ( Integer item : s1 ) {
            String sItem = (String) properties.get(item.toString());
            this.loadClass(sItem);
        }

        if (m_data.getCommands().isEmpty()) {
            throw new ExceptionNoPlugins();
        }
    }

    /**
     *
     * @param nameClass
     * @throws ClassNotFoundException
     */
    private void loadClass(String nameClass) throws
            IllegalAccessException,
            InstantiationException,
            ClassNotFoundException
    {
        Class<?> obj = Class.forName(nameClass);
        ICommand cmd = (ICommand) obj.newInstance();

        Field[] fields = cmd.getClass().getSuperclass().getDeclaredFields();

        for (Field field : fields) {
            KMGResource annotation = field.getAnnotation(KMGResource.class);
            if (annotation == null) { continue;}

            //
            // Initialize object
            //
            if ("CommandDataContainer".equals(annotation.type()))  {
                field.setAccessible(true);
                field.set(cmd, this.m_data);
            }
            m_data.getCommands().add(cmd);
        }
    }

    /**
     *
     * @param nameClass
     */
    /**
    private void loadClass(String nameClass) throws
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException,
            InstantiationException
    {
        try {
            Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(nameClass);
            ICommand obj = (ICommand)aClass.getConstructor(CommandDataContainer.class).newInstance(this.m_data);
            m_data.getCommands().add(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    */

    public CommandDataContainer getContainerData() {
        return this.m_data;
    }
}
