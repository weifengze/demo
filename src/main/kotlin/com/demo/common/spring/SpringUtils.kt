package com.demo.common.spring

import org.springframework.aop.framework.AopContext
import org.springframework.beans.BeansException
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class SpringUtils : BeanFactoryPostProcessor {
    companion object {
        /**
         * Spring应用上下文环境
         */
        private var beanFactory: ConfigurableListableBeanFactory? = null

        /**
         * 获取对象
         *
         * @param name
         * @return Object 一个以所给名字注册的bean的实例
         * @throws org.springframework.beans.BeansException
         */
        @Throws(BeansException::class)
        fun <T> getBean(name: String?): T {
            return beanFactory!!.getBean(name!!) as T
        }

        /**
         * 获取类型为requiredType的对象
         *
         * @param clz
         * @return
         * @throws org.springframework.beans.BeansException
         */
        @Throws(BeansException::class)
        fun <T> getBean(clz: Class<T>): T {
            return beanFactory!!.getBean(clz)
        }

        /**
         * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
         *
         * @param name
         * @return boolean
         */
        fun containsBean(name: String?): Boolean {
            return beanFactory!!.containsBean(name!!)
        }

        /**
         * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
         *
         * @param name
         * @return boolean
         * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
         */
        @Throws(NoSuchBeanDefinitionException::class)
        fun isSingleton(name: String?): Boolean {
            return beanFactory!!.isSingleton(name!!)
        }

        /**
         * @param name
         * @return Class 注册对象的类型
         * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
         */
        @Throws(NoSuchBeanDefinitionException::class)
        fun getType(name: String?): Class<*>? {
            return beanFactory!!.getType(name!!)
        }

        /**
         * 如果给定的bean名字在bean定义中有别名，则返回这些别名
         *
         * @param name
         * @return
         * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
         */
        @Throws(NoSuchBeanDefinitionException::class)
        fun getAliases(name: String?): Array<String?>? {
            return beanFactory!!.getAliases(name!!)
        }

        /**
         * 获取aop代理对象
         *
         * @param invoker
         * @return
         */
        fun <T> getAopProxy(invoker: T): T {
            return AopContext.currentProxy() as T
        }
    }

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        SpringUtils.beanFactory = beanFactory
    }
}