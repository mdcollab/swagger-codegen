package io.swagger.codegen.languages;

import io.swagger.codegen.*;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class JavaArmeriaCodegen extends AbstractJavaCodegen {

    public static final String TITLE = "title";
    public static final String CONFIG_PACKAGE = "configPackage";
    public static final String BASE_PACKAGE = "basePackage";
    public static final String CONTROLLER_ONLY = "controllerOnly";
    public static final String USE_INTERFACES = "useInterfaces";
    public static final String HANDLE_EXCEPTIONS = "handleExceptions";
    public static final String WRAP_CALLS = "wrapCalls";
    public static final String USE_SWAGGER_UI = "useSwaggerUI";

    protected String title = "swagger-petstore";
    protected String configPackage = "io.swagger.configuration";
    protected String basePackage = "io.swagger";
    protected boolean controllerOnly = false;
    protected boolean useInterfaces = true;
    protected boolean useBeanValidation = true;
    protected boolean handleExceptions = true;
    protected boolean wrapCalls = true;
    protected boolean useSwaggerUI = true;

    public JavaArmeriaCodegen() {
        super();

        outputFolder = "generated-code" + File.separator + "java-armeria";
        apiTestTemplateFiles.clear();
        embeddedTemplateDir = templateDir = "JavaArmeria";
        apiPackage = "io.swagger.api";
        modelPackage = "io.swagger.model";
        invokerPackage = "io.swagger.api";
        artifactId = "swagger-java-armeria";


        // FOR NOW!!
        apiTestTemplateFiles.remove("api_test.mustache");
        modelDocTemplateFiles.remove("model_doc.mustache");
        apiDocTemplateFiles.remove("api_doc.mustache");


        additionalProperties.put(CONFIG_PACKAGE, configPackage);
        additionalProperties.put(BASE_PACKAGE, basePackage);
        additionalProperties.put("java8", true);

        cliOptions.add(new CliOption(TITLE, "server title name or client service name"));
        cliOptions.add(new CliOption(CONFIG_PACKAGE, "configuration package for generated code"));
        cliOptions.add(new CliOption(BASE_PACKAGE, "base package for generated code"));

    }

    @Override
    public Map<String, Object> postProcessOperations(Map<String, Object> objs) {
        Map<String, Object> operations = (Map<String, Object>) objs.get("operations");
        if (operations != null) {
            List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
            for (final CodegenOperation operation : ops) {

                // Capitalizes the first letter to form the annotations of Armeria
                operation.httpMethod = operation.httpMethod.substring(0, 1) + operation.httpMethod.substring(1).toLowerCase();
            }
        }

        return objs;
    }

    @Override
    public void processOpts() {
        super.processOpts();

        // clear model and api doc template as this codegen
        // does not support auto-generated markdown doc at the moment
        //modelDocTemplateFiles.remove("model_doc.mustache");
        //apiDocTemplateFiles.remove("api_doc.mustache");

        if (additionalProperties.containsKey(TITLE)) {
            this.setTitle((String) additionalProperties.get(TITLE));
        }

        if (additionalProperties.containsKey(CONFIG_PACKAGE)) {
            this.setConfigPackage((String) additionalProperties.get(CONFIG_PACKAGE));
        }

        if (additionalProperties.containsKey(BASE_PACKAGE)) {
            this.setBasePackage((String) additionalProperties.get(BASE_PACKAGE));
        }

        /**
         * We can add these manually as well (dependencies)
        supportingFiles.add(new SupportingFile("pom.mustache", "", "pom.xml"));
        supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));
         supportingFiles.add(new SupportingFile("build.gradle.mustache", "", "build.gradle"));
         supportingFiles.add(new SupportingFile("settings.gradle.mustache", "", "settings.gradle"));
         supportingFiles.add(new SupportingFile("gradle.properties", "", "gradle.properties"));
         */
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.SERVER;
    }

    @Override
    public String getName() {
        return "java-armeria";
    }

    @Override
    public String getHelp() {
        return "Generates a Java Armeria Server Application.";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConfigPackage(String configPackage) {
        this.configPackage = configPackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setControllerOnly(boolean controllerOnly) {
        this.controllerOnly = controllerOnly;
    }

    public void setUseInterfaces(boolean useInterfaces) {
        this.useInterfaces = useInterfaces;
    }

    public void setUseBeanValidation(boolean useBeanValidation) {
        this.useBeanValidation = useBeanValidation;
    }

    public void setHandleExceptions(boolean handleExceptions) {
        this.handleExceptions = handleExceptions;
    }

    public void setWrapCalls(boolean wrapCalls) {
        this.wrapCalls = wrapCalls;
    }

    public void setUseSwaggerUI(boolean useSwaggerUI) {
        this.useSwaggerUI = useSwaggerUI;
    }
}
