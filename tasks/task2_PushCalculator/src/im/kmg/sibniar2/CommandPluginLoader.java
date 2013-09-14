package im.kmg.sibniar2;

import im.kmg.sibniar2.commands.CommandDataContainer;
import im.kmg.sibniar2.commands.ICommand;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
            IllegalAccessException
    {
        FileReader fileReader = new FileReader(fileName);
        Properties properties = new Properties();
        properties.load(fileReader);

        for ( Object obj : properties.keySet() ) {
            this.loadClass((String) properties.get(obj));
        }
    }

    /**
     *
     * @param nameClass
     */
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

    public CommandDataContainer getContainerData() {
        return this.m_data;
    }
}
