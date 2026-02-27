package br.com.ednei.bipedneims.config;

import br.com.ednei.bip.ejb.BeneficioServiceRemote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class EjbClientConfig {

    @Bean
    public BeneficioServiceRemote beneficioServiceRemote() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL, "http-remoting://localhost:8180");
        Context ctx = new InitialContext(props);

        String jndi = "ejb:/bip-ejb/BeneficioService!br.com.ednei.bip.ejb.BeneficioServiceRemote";
        return (BeneficioServiceRemote) ctx.lookup(jndi);
    }
}