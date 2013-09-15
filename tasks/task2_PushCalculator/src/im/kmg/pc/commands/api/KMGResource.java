package im.kmg.pc.commands.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: maxim
 * Date: 9/14/13
 * Time: 7:59 PM
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface KMGResource {
    String type();
}
