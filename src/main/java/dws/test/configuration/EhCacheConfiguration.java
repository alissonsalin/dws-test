package dws.test.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class EhCacheConfiguration {
	

	@Value("${cache.timeToIdleSeconds}")
	private Integer cacheTimeToIdleSeconds;
	
	@Value("${cache.timeToLiveSeconds}")
	private Integer cacheTimeToLiveSeconds;
	
	@Value("${cache.maxEntriesLocalHeap}")
	private Integer cacheMaxEntriesLocalHeap;
	
	
	
	@Bean
    public EhCacheManagerFactoryBean cacheManager() {
        return new EhCacheManagerFactoryBean();
    }
	     
    @Bean
    public EhCacheCacheManager testEhCacheManager() {
        CacheConfiguration ehCacheConfigBandsByName = new CacheConfiguration()
            .eternal(false)                    
            .timeToIdleSeconds(cacheTimeToIdleSeconds)           
            .timeToLiveSeconds(cacheTimeToLiveSeconds)        
            .maxEntriesLocalHeap(cacheMaxEntriesLocalHeap)   
            .memoryStoreEvictionPolicy("LRU")
            .name("bandsByName");
        
        CacheConfiguration ehCacheConfigBandsByNameSorted = new CacheConfiguration()
	            .eternal(false)
	            .timeToIdleSeconds(cacheTimeToIdleSeconds)
	            .timeToLiveSeconds(cacheTimeToLiveSeconds)
	            .maxEntriesLocalHeap(cacheMaxEntriesLocalHeap)
	            .memoryStoreEvictionPolicy("LRU")
	            .name("bandsByNameSorted");
         
        cacheManager().getObject().addCache(new Cache(ehCacheConfigBandsByName));
        cacheManager().getObject().addCache(new Cache(ehCacheConfigBandsByNameSorted));
        return new EhCacheCacheManager(cacheManager().getObject());
    }
}
