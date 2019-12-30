//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mdx.andplus.common;

import com.intellij.openapi.vfs.VirtualFile;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class CreateFitXml {
    public static CreateFitXml.ScreenF[] Screens = new CreateFitXml.ScreenF[]{new CreateFitXml.ScreenF(320, 533), new CreateFitXml.ScreenF(360, 853), new CreateFitXml.ScreenF(384, 844), new CreateFitXml.ScreenF(400, 720), new CreateFitXml.ScreenF(432, 768), new CreateFitXml.ScreenF(480, 853), new CreateFitXml.ScreenF(411, 731), new CreateFitXml.ScreenF(540, 900), new CreateFitXml.ScreenF(600, 960), new CreateFitXml.ScreenF(720, 960), new CreateFitXml.ScreenF(768, 1280), new CreateFitXml.ScreenF(800, 1280)};
    private float Distinguish = 0.0F;

    public CreateFitXml(List<CreateFitXml.Dimclass> dplist, List<CreateFitXml.Dimclass> splist, VirtualFile resFile, float Distinguish) throws Exception {
        this.Distinguish = Distinguish;
        VirtualFile valuefile = resFile.findChild("values");
        if (valuefile == null || !valuefile.exists()) {
            valuefile = resFile.createChildDirectory((Object)null, "values");
        }

        this.mkFile(valuefile, 1.0F, "dp", dplist, false);
        CreateFitXml.ScreenF[] var6 = Screens;
        int var7 = var6.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            CreateFitXml.ScreenF scr = var6[var8];
            String pn = "values-sw" + scr.width + "dp";
            valuefile = resFile.findChild(pn);
            if (valuefile == null || !valuefile.exists()) {
                valuefile = resFile.createChildDirectory((Object)null, pn);
            }

            if (this.Distinguish == 0.0F) {
                this.Distinguish = 320.0F;
            }

            this.mkFile(valuefile, (float)scr.width * 1.0F / this.Distinguish, "dp", dplist, true);
        }

    }

    public void mkFile(VirtualFile path, float js, String dw, List<CreateFitXml.Dimclass> list, boolean nn) throws Exception {
        VirtualFile xml = path.findOrCreateChildData((Object)null, dw + "dim.xml");
        Element root = new Element("resources");
        Iterator var8 = list.iterator();

        while(true) {
            while(var8.hasNext()) {
                CreateFitXml.Dimclass flo = (CreateFitXml.Dimclass)var8.next();
                Element ele;
                if (nn) {
                    ele = new Element("dimen");
                    if (flo.name == null || flo.name.length() == 0) {
                        flo.name = getDimName(flo.value, dw);
                    }

                    ele.setAttribute("name", flo.name);
                    ele.setText(flo.value * js + dw);
                    root.addContent(ele);
                } else if (flo.name == null || flo.name.length() == 0) {
                    flo.name = getDimName(flo.value, dw);
                    ele = new Element("dimen");
                    ele.setAttribute("name", flo.name);
                    ele.setText(flo.value * js + dw);
                    root.addContent(ele);
                }
            }

            Document doc = new Document();
            doc.addContent(root);
            Format format = Format.getCompactFormat();
            format.setIndent("     ");
            format.setEncoding("UTF-8");
            XMLOutputter out = new XMLOutputter(format);
            OutputStreamWriter fw = new OutputStreamWriter(xml.getOutputStream((Object)null), "UTF-8");
            out.output(doc, fw);
            fw.close();
            return;
        }
    }

    public static String getDimName(float f, String dw) {
        float lw = f - (float)((int)f);
        return lw == 0.0F ? ((f > 0.0F ? "j" : "f") + (int)f + dw).replace(".", "_").replace("-", "") : ((f > 0.0F ? "j" : "f") + f + dw).replace(".", "_").replace("-", "");
    }

    public static class Dimclass {
        public String name = null;
        public float value = 0.0F;

        public Dimclass(String n, float v) {
            this.name = n;
            this.value = v;
        }

        public Dimclass(float v) {
            this.value = v;
        }
    }

    public static class ScreenF {
        int width = 320;
        int height = 480;

        public ScreenF(int w, int h) {
            this.width = w;
            this.height = h;
        }
    }
}
