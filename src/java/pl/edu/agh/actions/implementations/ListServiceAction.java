package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.objects.services.Services;
import pl.edu.agh.objects.services.implemetations.*;

import java.util.ArrayList;
import java.util.List;

public class ListServiceAction extends Action {

    @Override
    public ActionResult doOperation() {
        try {
            List<Services> services = new ArrayList<>();

            services.add(new CutAndStyleMen());
            services.add(new CutAndStyleWomen());
            services.add(new TextureAndCurlWomen());
            services.add(new ValuePackagesMen());
            services.add(new ColorMen());
            services.add(new ColorWomen());
            services.add(new ValuePackagesWomen());
            services.add(new ExtrasMen());
            services.add(new ExtrasWomen());

            ActionResult actionResult = new ActionResult("OK");
            actionResult.setReturnObject(services);
            return actionResult;

        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
    }
}
