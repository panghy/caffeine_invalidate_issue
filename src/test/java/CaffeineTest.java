import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.HOURS;

/**
 * Created by clement on 8/12/2015.
 */
public class CaffeineTest {

  @Test
  public void testInvalidate() {
    LoadingCache<Object, String> cache = Caffeine.newBuilder().expireAfterAccess(1, HOURS).build(
        new CacheLoader<Object, String>() {
          public String load(Object obj) throws Exception {
            return "hello";
          }
        });
    cache.get("hello");
    cache.get("world");
    cache.invalidate("hello");
    cache.invalidate("world");
    cache.invalidate("hello");
    cache.invalidate("world");
  }
}
