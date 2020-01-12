package p4.groovy

import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.context.support.GenericApplicationContext
import p4.groovy.Singer

def ctx = new GenericApplicationContext()
def reader = new GroovyBeanDefinitionReader(ctx)


reader.beans {
    singer(Singer, name: 'John Snow', age: 25)
}

ctx.refresh();
println ctx.getBean("singer")