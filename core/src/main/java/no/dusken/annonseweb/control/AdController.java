/*
 * Copyright 2013 Studentmediene i Trondheim AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.dusken.annonseweb.control;

import no.dusken.annonseweb.models.*;
import no.dusken.annonseweb.service.AdService;
import no.dusken.annonseweb.service.SalesService;
import no.dusken.common.editor.BindByIdEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("ad/")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private SalesService salesService;

    @RequestMapping("")
    public String viewAdHome(){
        return "ad/home";
    }

    @RequestMapping("new")
    public String viewNewAd(){
        return "ad/new";
    }

    @RequestMapping("{ad}")
    public String viewAd(@PathVariable Ad ad, Model model){
        model.addAttribute("ad", ad);
        if(ad instanceof PrintedAd){
            return "ad/ad/printed";
        }
        else if (ad instanceof WebAd){
            return "ad/ad/web";
        }
        else if (ad instanceof RadioAd){
            return "ad/ad/radio";
        }
        return "";
    }

    @RequestMapping("overview")
    public String viewAdListActive(Model model){
        model.addAttribute("adList", adService.getActiveAds());
        return "ad/overview";
    }

    @RequestMapping("all")
    public String viewAdListAll(Model model){
        model.addAttribute("adList", adService.findAll());
        return "ad/all";
    }

    @RequestMapping("new/printed")
    public String viewNewPrintedAd(Model model){
        return viewEdit(new PrintedAd(), model);
    }

    @RequestMapping("new/web")
    public String viewNewWebAd(Model model){
        return viewEdit(new WebAd(), model);
    }

    @RequestMapping("new/radio")
    public String viewNewRadioAd(Model model){
        return viewEdit(new RadioAd(), model);
    }

    @RequestMapping("edit/{ad}")
    public String viewEdit(@PathVariable Ad ad, Model model){
        model.addAttribute("salesList", salesService.getActiveSales());
        model.addAttribute("ad", ad);
        if (ad instanceof PrintedAd) {
            return "ad/edit/printed";
        }
        else if (ad instanceof WebAd) {
            return "ad/edit/web";
        }
        else if (ad instanceof RadioAd) {
            return "ad/edit/radio";
        }
        return "";
    }

    @RequestMapping(value = "/save/web", method = RequestMethod.POST)
    public String saveNewWeb(@Valid @ModelAttribute WebAd ad) {
        adService.saveAndFlush((WebAd) ad);
        return "redirect:/annonseweb/ad/" + ad.getId();
    }

    @RequestMapping(value = "/save/printed", method = RequestMethod.POST)
    public String saveNewPrinted(@Valid @ModelAttribute PrintedAd ad) {
        adService.saveAndFlush((PrintedAd) ad);
        return "redirect:/annonseweb/ad/" + ad.getId();
    }

    @RequestMapping(value = "/save/radio", method = RequestMethod.POST)
    public String saveNewRadio(@Valid @ModelAttribute RadioAd ad) {
        adService.saveAndFlush((RadioAd) ad);
        return "redirect:/annonseweb/ad/" + ad.getId();
    }

    @RequestMapping("save/web/{pathAd}")
    public String saveEditWeb(@PathVariable WebAd pathAd, @Valid @ModelAttribute WebAd ad) {
        pathAd.cloneFrom((WebAd) ad);
        adService.saveAndFlush((WebAd) pathAd);
        return "redirect:/annonseweb/ad/" + pathAd.getId();
    }

    @RequestMapping("save/printed/{pathAd}")
    public String saveEditPrinted(@PathVariable PrintedAd pathAd, @Valid @ModelAttribute PrintedAd ad) {
        pathAd.cloneFrom((PrintedAd) ad);
        adService.saveAndFlush((PrintedAd) pathAd);
        return "redirect:/annonseweb/ad/" + pathAd.getId();
    }

    @RequestMapping("save/radio/{pathAd}")
    public String saveEditRadio(@PathVariable RadioAd pathAd, @Valid @ModelAttribute RadioAd ad) {
        pathAd.cloneFrom((RadioAd) ad);
        adService.saveAndFlush((RadioAd) pathAd);
        return "redirect:/annonseweb/ad/" + pathAd.getId();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Ad.class, new BindByIdEditor(adService));
        binder.registerCustomEditor(Sale.class, new BindByIdEditor(salesService));
    }
}