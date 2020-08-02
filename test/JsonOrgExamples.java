import org.junit.Test;

import java.io.File;

/**
 * Tests from https://json.org/example.html
 */
public class JsonOrgExamples extends BaseTestClass {

    private final String resourcesPath = super.resourcesPath + "/examples";

    @Test
    public void Example1() throws Exception {
        File file = new File(resourcesPath, "example1.json");
        JSONObject expected = new JSONObject() {{
            put("glossary", new JSONObject() {{
                put("title", "example glossary");
                put("GlossDiv", new JSONObject() {{
                    put("title", "S");
                    put("GlossList", new JSONObject() {{
                        put("GlossEntry", new JSONObject() {{
                            put("ID", "SGML");
                            put("SortAs", "SGML");
                            put("GlossTerm", "Standard Generalized Markup Language");
                            put("Acronym", "SGML");
                            put("Abbrev", "ISO 8879:1986");
                            put("GlossDef", new JSONObject() {{
                                put("para", "A meta-markup language, used to create markup languages such as DocBook.");
                                put("GlossSeeAlso", new JSONArray() {{
                                    add("GML");
                                    add("XML");
                                }});
                            }});
                            put("GlossSee", "markup");
                        }});
                    }});
                }});
            }});
        }};
        parseAndCompareObjects("Example1", file, expected);
    }

    @Test
    public void Example2() throws Exception {
        File file = new File(resourcesPath, "example2.json");
        JSONObject expected = new JSONObject() {{
            put("menu", new JSONObject() {{
                put("id", "file");
                put("value", "File");
                put("popup", new JSONObject() {{
                    put("menuitem", new JSONArray() {{
                        add(new JSONObject() {{
                            put("value", "New");
                            put("onclick", "CreateNewDoc()");
                        }});
                        add(new JSONObject() {{
                            put("value", "Open");
                            put("onclick", "OpenDoc()");
                        }});
                        add(new JSONObject() {{
                            put("value", "Close");
                            put("onclick", "CloseDoc()");
                        }});
                    }});
                }});
            }});
        }};
        parseAndCompareObjects("Example2", file, expected);
    }

    @Test
    public void Example3() throws Exception {
        File file = new File(resourcesPath, "example3.json");
        JSONObject expected = new JSONObject() {{
            put("widget", new JSONObject() {{
                put("debug", "on");
                put("window", new JSONObject() {{
                    put("title", "Sample Konfabulator Widget");
                    put("name", "main_window");
                    put("width", 500);
                    put("height", 500);
                }});
                put("image", new JSONObject() {{
                    put("src", "Images/Sun.png");
                    put("name", "sun1");
                    put("hOffset", 250);
                    put("vOffset", 250);
                    put("alignment", "center");
                }});
                put("text", new JSONObject() {{
                    put("data", "Click Here");
                    put("size", 36);
                    put("style", "bold");
                    put("name", "text1");
                    put("hOffset", 250);
                    put("vOffset", 100);
                    put("alignment", "center");
                    put("onMouseUp", "sun1.opacity = (sun1.opacity / 100) * 90;");
                }});
            }});
        }};
        parseAndCompareObjects("Example3", file, expected);
    }

    @Test
    public void Example4() throws Exception {
        File file = new File(resourcesPath, "example4.json");
        JSONObject expected = new JSONObject() {{
            put("web-app", new JSONObject() {{
                put("servlet", new JSONArray() {{
                    add(new JSONObject() {{
                        put("servlet-name", "cofaxCDS");
                        put("servlet-class", "org.cofax.cds.CDSServlet");
                        put("init-param", new JSONObject() {{
                            put("configGlossary:installationAt", "Philadelphia, PA");
                            put("configGlossary:adminEmail", "ksm@pobox.com");
                            put("configGlossary:poweredBy", "Cofax");
                            put("configGlossary:poweredByIcon", "/images/cofax.gif");
                            put("configGlossary:staticPath", "/content/static");
                            put("templateProcessorClass", "org.cofax.WysiwygTemplate");
                            put("templateLoaderClass", "org.cofax.FilesTemplateLoader");
                            put("templatePath", "templates");
                            put("templateOverridePath", "");
                            put("defaultListTemplate", "listTemplate.htm");
                            put("defaultFileTemplate", "articleTemplate.htm");
                            put("useJSP", false);
                            put("jspListTemplate", "listTemplate.jsp");
                            put("jspFileTemplate", "articleTemplate.jsp");
                            put("cachePackageTagsTrack", 200);
                            put("cachePackageTagsStore", 200);
                            put("cachePackageTagsRefresh", 60);
                            put("cacheTemplatesTrack", 100);
                            put("cacheTemplatesStore", 50);
                            put("cacheTemplatesRefresh", 15);
                            put("cachePagesTrack", 200);
                            put("cachePagesStore", 100);
                            put("cachePagesRefresh", 10);
                            put("cachePagesDirtyRead", 10);
                            put("searchEngineListTemplate", "forSearchEnginesList.htm");
                            put("searchEngineFileTemplate", "forSearchEngines.htm");
                            put("searchEngineRobotsDb", "WEB-INF/robots.db");
                            put("useDataStore", true);
                            put("dataStoreClass", "org.cofax.SqlDataStore");
                            put("redirectionClass", "org.cofax.SqlRedirection");
                            put("dataStoreName", "cofax");
                            put("dataStoreDriver", "com.microsoft.jdbc.sqlserver.SQLServerDriver");
                            put("dataStoreUrl", "jdbc:microsoft:sqlserver://LOCALHOST:1433;DatabaseName=goon");
                            put("dataStoreUser", "sa");
                            put("dataStorePassword", "dataStoreTestQuery");
                            put("dataStoreTestQuery", "SET NOCOUNT ON;select test='test';");
                            put("dataStoreLogFile", "/usr/local/tomcat/logs/datastore.log");
                            put("dataStoreInitConns", 10);
                            put("dataStoreMaxConns", 100);
                            put("dataStoreConnUsageLimit", 100);
                            put("dataStoreLogLevel", "debug");
                            put("maxUrlLength", 500);
                        }});
                    }});
                    add(new JSONObject() {{
                        put("servlet-name", "cofaxEmail");
                        put("servlet-class", "org.cofax.cds.EmailServlet");
                        put("init-param", new JSONObject() {{
                            put("mailHost", "mail1");
                            put("mailHostOverride", "mail2");
                        }});
                    }});
                    add(new JSONObject() {{
                        put("servlet-name", "cofaxAdmin");
                        put("servlet-class", "org.cofax.cds.AdminServlet");
                    }});
                    add(new JSONObject() {{
                        put("servlet-name", "fileServlet");
                        put("servlet-class", "org.cofax.cds.FileServlet");
                    }});
                    add(new JSONObject() {{
                        put("servlet-name", "cofaxTools");
                        put("servlet-class", "org.cofax.cms.CofaxToolsServlet");
                        put("init-param", new JSONObject() {{
                            put("templatePath", "toolstemplates/");
                            put("log", 1);
                            put("logLocation", "/usr/local/tomcat/logs/CofaxTools.log");
                            put("logMaxSize", "");
                            put("dataLog", 1);
                            put("dataLogLocation", "/usr/local/tomcat/logs/dataLog.log");
                            put("dataLogMaxSize", "");
                            put("removePageCache", "/content/admin/remove?cache=pages&id=");
                            put("removeTemplateCache", "/content/admin/remove?cache=templates&id=");
                            put("fileTransferFolder", "/usr/local/tomcat/webapps/content/fileTransferFolder");
                            put("lookInContext", 1);
                            put("adminGroupID", 4);
                            put("betaServer", true);
                        }});
                    }});
                }});
                put("servlet-mapping", new JSONObject() {{
                    put("cofaxCDS", "/");
                    put("cofaxEmail", "/cofaxutil/aemail/*");
                    put("cofaxAdmin", "/admin/*");
                    put("fileServlet", "/static/*");
                    put("cofaxTools", "/tools/*");
                }});
                put("taglib", new JSONObject() {{
                    put("taglib-uri", "cofax.tld");
                    put("taglib-location", "/WEB-INF/tlds/cofax.tld");
                }});
            }});
        }};
        parseAndCompareObjects("Example4", file, expected);
    }

    @Test
    public void Example5() throws Exception {
        File file = new File(resourcesPath, "example5.json");
        JSONObject expected = new JSONObject() {{
            put("menu", new JSONObject() {{
                put("header", "SVG Viewer");
                put("items", new JSONArray() {{
                    add(new JSONObject() {{
                        put("id", "Open");
                    }});
                    add(new JSONObject() {{
                        put("id", "OpenNew");
                        put("label", "Open New");
                    }});
                    add(null);
                    add(new JSONObject() {{
                        put("id", "ZoomIn");
                        put("label", "Zoom In");
                    }});
                    add(new JSONObject() {{
                        put("id", "ZoomOut");
                        put("label", "Zoom Out");
                    }});
                    add(new JSONObject() {{
                        put("id", "OriginalView");
                        put("label", "Original View");
                    }});
                    add(null);
                    add(new JSONObject() {{
                        put("id", "Quality");
                    }});
                    add(new JSONObject() {{
                        put("id", "Pause");
                    }});
                    add(new JSONObject() {{
                        put("id", "Mute");
                    }});
                    add(null);
                    add(new JSONObject() {{
                        put("id", "Find");
                        put("label", "Find...");
                    }});
                    add(new JSONObject() {{
                        put("id", "FindAgain");
                        put("label", "Find Again");
                    }});
                    add(new JSONObject() {{
                        put("id", "Copy");
                    }});
                    add(new JSONObject() {{
                        put("id", "CopyAgain");
                        put("label", "Copy Again");
                    }});
                    add(new JSONObject() {{
                        put("id", "CopySVG");
                        put("label", "Copy SVG");
                    }});
                    add(new JSONObject() {{
                        put("id", "ViewSVG");
                        put("label", "View SVG");
                    }});
                    add(new JSONObject() {{
                        put("id", "ViewSource");
                        put("label", "View Source");
                    }});
                    add(new JSONObject() {{
                        put("id", "SaveAs");
                        put("label", "Save As");
                    }});
                    add(null);
                    add(new JSONObject() {{
                        put("id", "Help");
                    }});
                    add(new JSONObject() {{
                        put("id", "About");
                        put("label", "About Adobe CVG Viewer...");
                    }});
                }});
            }});
        }};
        parseAndCompareObjects("Example5", file, expected);
    }
}
