package com.zinminthet.plantguardai.bootstrap;


import com.zinminthet.plantguardai.entities.*;
import com.zinminthet.plantguardai.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitialization implements CommandLineRunner {

    private final MerchantRepository merchantRepository;
    private final FarmerRepository farmerRepository;
    private final ShopRepository shopRepository;
    private final RoleRepository roleRepository;
    private final AuthRepository authRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final DiseaseRepository diseaseRepository;
    private final CureRepository cureRepository;
    private final PesticideRepository pesticideRepository;



    @Transactional
    @Override
    public void run(String... args) throws Exception {
//        populateRole(List.of("admin", "farmer", "merchant"));
//        populateFarmer("Kyaw Thu", "kyawthu022004@gmail.com", "kyawthu", "64th street");
//        populateMerchant("Nara Kyaw Swar", "9/khamasa(N)/123123", "nrks@gmail.com", "nrks", List.of("Nara Limited", "Nara Family"));
//        populateAdmin("Zin Min Thet", "zinminthet.software.engineer@gmail.com", "admin");



        populateDisease("စပါးဘတ်တီးရီးယားရွက်ခြောက်ရောဂါ","ဘတ်တီးရီးယား","လင်းနစ် ၂၀ ဒဗလျူပီ","စပါးနှင့် သီးနှံမျိုးစုံတွင် ကျရောက်သော ဘတ်တီးရီးယားရောဂါ အမျိုးမျိုးကို ကာကွယ်ကုသနိုင်သော ဘတ်တီးရီးယားရောဂါသတ်ဆေးဖြစ်သည်။",4500.78,"Oxolinic Acid 20%", "pesticides/လင်းနစ် ၂၀ ဒဗလျူပီ.jpg","မျိုးစေ့ဆောင်ရောဂါဖြစ်သည့်အတွက် ရောဂါကင်းသော ခံနိုင်ရည်ရှိသော မျိုးကောင်းမျိုးသန့်များကို စိုက်ပျိုးပါ။,ရောဂါဖြစ်စေသော သက်ရှိ ခိုအောင်းနိုင်သော လက်ခံပင်များကို ရှင်းလင်းပစ်ပါ။,စိုက်ပျိုးကတည်းက မြေပြင်ကို မြေဆီလုံလောက်စွာ ပြုပြင်၍ စိုက်ပျိုးပါ။","250g");
        populateDisease("စပါးရွက်ညိုရွက်ပြောက်ရောဂါ","ဘတ်တီးရီးယား","ဇိုလာ ၂၅ အီးစီ","သီးနှံအမျိုးမျိုးတွင် ကျရောက်ဖျက်ဆီးသည့် မှိုရောဂါများကို ကာကွယ်ကုသသောပင်လုံးပြန့် အာနိသင်ရှိ မှိုသတ်ဆေးဖြစ်သည်။",5000.99,"Difenoconazole 25%","pesticides/ဇိုလာ ၂၅ အီးစီ.jpg","စပါးဘတ်တီးရီးယားရွက်ခြောက်ရောဂါ ကာကွယ်ရန်အကောင်းဆုံးနည်းမှာ ရောဂါခံနိုင်ရည်ရှိသော စပါးမျိုးများ စိုက်ပျိုးခြင်းဖြစ်သည်။,နိုက်တြိုဂျင်ဓါတ်မြေသြဇာ လျော့သုံးရမည်ဖြစ်ပြီး ပိုတက်မြေသြဇာကို များများသုံးပေးရန်။,BLBရောဂါကျရောက်လျင် Kasugamycin နှင့် Copper ပါဝင်သောမှိုသတ်ဆေးများအသုံးပြုပတ်ဖြန်းပါ။","250g");
        populateDisease("စပါးရွက်ညိုရွက်ပြောက်ရောဂါ","ဘတ်တီးရီးယား","ဘင်ဇိုကာ၅၅၀ အက်စ်စီ","သအာလူး၊ ခရမ်းချဉ်၊ ဟင်းသီးဟင်းရွက်နှင့် စပါးတို့တွင် ကျရောက်သည့်မှိုရောဂါများကို ထိရောက်စွာ ကာကွယ်နှိမ်နင်းပေးနိုင်သော မှိုသတ်ဆေးရည် ဖြစ်သည်။",6000.99,"Chlorothalonil 45%,Hexaconazole 3.5%,Cymoxanil 6.5%","pesticides/ဘင်ဇိုကာ၅၅၀ အက်စ်စီ.jpg","စပါးဘတ်တီးရီးယားရွက်ခြောက်ရောဂါ ကာကွယ်ရန်အကောင်းဆုံးနည်းမှာ ရောဂါခံနိုင်ရည်ရှိသော စပါးမျိုးများ စိုက်ပျိုးခြင်းဖြစ်သည်။,နိုက်တြိုဂျင်ဓါတ်မြေသြဇာ လျော့သုံးရမည်ဖြစ်ပြီး ပိုတက်မြေသြဇာကို များများသုံးပေးရန်။,BLBရောဂါကျရောက်လျင် Kasugamycin နှင့် Copper ပါဝင်သောမှိုသတ်ဆေးများအသုံးပြုပတ်ဖြန်းပါ။","250g");

        populateDisease("စပါးရွက်လောင်ရောဂါ","ဘတ်တီးရီးယား","ကျူပရို-မိုင်စင် ၄၇၀ ဒဗလျူပီ","ထိသေနှင့်ပင်လုံးပြန့်အာနိသင်ရှိ ဘက်တီးရီးယားနှင့် မှိုရောဂါ ကာကွယ်ကုသဆေးဖြစ်သည်။ ",4999.99,"Copper oxychloride 45 % ,Kasugamycin 2 %","pesticides/ကျူပရို-မိုင်စင် ၄၇၀ ဒဗလျူပီ.jpg","ရောဂါကင်းစင်သောမျိုးစေ့ကို အသုံးပြုပါ။ နိုက်ထရိုဂျင်ဓာတ်မြေဩဇာ (ယူရီးယား) အလွန်အကျွံသုံးခြင်းကို ရှောင်ကြဉ်ပါ။ စိုက်သိပ်သည်းဆ လျှော့ချပါ။,ရောဂါရ အပင်ဟောင်းများကို ဖျက်ဆီးပါ။, လိုအပ်ပါက မှိုသတ်ဆေးများ (ဥပမာ- Hexaconazole, Validamycin) ကို လမ်းညွှန်ချက်အတိုင်း အသုံးပြုပါ။ ","250g");
        populateDisease("စပါးခေါက်ရောဂါ (စပါးခွံနာကျဲရောဂါ)","ဘတ်တီးရီးယား","MLM Mancozeb 80% WP","ဖရဲမျိုးများတွင် ကျရောက်တတ်သည့် မှဲ့ပြောက်စွန်းရောဂါ၊ စပါးဂုတ်ကျိုးရောဂါ၊ ပဲမျိုးစုံ၊ ငရုတ်၊ အာလူး၊ ခရမ်းချဉ်၊ ကြက်သွန်ဖြူ/နီ၊ ကွမ်းမြေပဲနှင့် သစ်သီး၀လံ၊ ပန်းမန်များတွင် ကျရောက်တတ်သည့် မှိုရောဂါအမျိုးမျိုး ကာကွယ် နှိမ်နှင်းပေးနိုင်သည်။",7888.99,"Mancozeb 80%","pesticides/MLM Mancozeb 80% WP.jpg","စပါးရွက်လိပ်/ရွက်ခေါက်ပိုးခံနိုင်ရည်ရှိတဲ့ စပါးမျိုးများကို စိုက်ပျိုးပါ။,စပါးရိတ်သိမ်းပြီးတဲ့နောက်မှာ စပါးခင်းကို ရေလွှမ်းပြီး ထယ်ထိုးပါ။,စပါးခင်းနဲ့ ကန်သင်းပေါ်က မြက်မျိုးနွယ်ဝင်ပေါင်းတွေကို ဖယ်ရှားပါ။,စပါးနဲ့မတူတဲ့ အခြားသီးနှံတွေနဲ့ သီးလှည့်စိုက်ပါ ဒါမှမဟုတ် ဘာမှမစိုက်ဘဲထားတဲ့ကာလ ကြာရှည်ပါစေ။,လမိုင်းထားခြင်းကို ရှောင်ရှားပါ။","260g");
        populateDisease("စပါးရွက်ခြောက်ရောဂါ","ဘတ်တီးရီးယား","ကာစင် ၂ အက်စ်အယ်","ပင်လုံးပြန့်အာနိသင်ရှိ ကာကွယ်မှုနှင့် ကုသမှုပေးနိုင်သော မှိုနှင့်ဘက်တီးရီးယား ရောဂါသတ်ဆေးရည်ဖြစ်သည်။",788.00,"Kasugamycin 2%","pesticides/ကာစင် ၂ အက်စ်အယ်.jpg","ရောဂါကင်းစင်သော မျိုးစေ့ကို အသုံးပြုပါ။,စပါးပင်များ ကျပ်မနေစေရန် စိုက်ပျိုးစိပ်ခြင်းကို ရှောင်ကြဉ်ပါ။,ရောဂါလက္ခဏာများ စတင်တွေ့ရှိပါက ကော့ပါးအောက်စီကလိုရိုက် (Copper oxychloride) ကဲ့သို့သော ဘက်တီးရီးယားသတ်ဆေးများကို အသုံးပြုနိုင်သည်။","250g");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");
        populateDisease("Healthy","ဘတ်တီးရီးယား","","",0.00,"","","","");

    }

    @Transactional
    public void populateRole(List<String> roles){

        var prefix = "ROLE_";
        if (!roles.isEmpty()){
            for(var role: roles) {
                var newRole = new Role();
                newRole.setName(prefix + role );
                newRole.setDescription("Role created by " + getClass().getSimpleName());
                roleRepository.save(newRole);
            }
        }
    }


    @Transactional
    public void populateAdmin(String name, String email, String password){
        var adminRole = roleRepository.findByName("ROLE_admin").get();

        var auth = new Auth();
        auth.setEmail(email);
        auth.setPassword(passwordEncoder.encode(password));
        auth.setEmailVerified(true);
        auth.setRole(adminRole);

        authRepository.save(auth);

        var admin = new Admin();
        admin.setId(auth.getId());
        admin.setName(name);
        admin.setAuth(auth);

        adminRepository.save(admin);
    }


    @Transactional
    public void populateFarmer(String name, String email, String password, String address){
        var farmerRole = roleRepository.findByName("ROLE_farmer").get();
        var auth = new Auth();
        auth.setEmail(email);
        auth.setPassword(String.format("{noop}%s", password));
        auth.setEmailVerified(true);
        auth.setRole(farmerRole);

        authRepository.save(auth);

        var farmer = new Farmer();
        farmer.setId(auth.getId());
        farmer.setAddress(address);
        farmer.setName(name);
        farmer.setAuth(auth);

        farmerRepository.save(farmer);

    }


    @Transactional
    public void populateMerchant(String name, String nrc, String email, String password, List<String> shopNames){
        var farmerRole = roleRepository.findByName("ROLE_merchant").get();
        var auth = new Auth();
        auth.setEmail(email);
        auth.setPassword(String.format("{noop}%s", password));
        auth.setEmailVerified(true);
        auth.setRole(farmerRole);

        authRepository.save(auth);

        var merchant = new Merchant();
        merchant.setId(auth.getId());
        merchant.setName(name);
        merchant.setName(nrc);

        merchantRepository.save(merchant);

        if(!shopNames.isEmpty()){
            for(var shopName: shopNames) {
                var shop = new Shop();
                shop.setMerchant(merchant);
                shop.setName(shopName);
                shop.setDescription("Owned by" + merchant.getName());
                shop.setAddress("default street");

                shopRepository.save(shop);
            }
        }


    }


    public void populateDisease(
            String diseaseName, String virusName, String pesticideName, String pesticideInfo, Double pesticidePrice,
            String pesticideIngredient, String pesticideImagePath, String prevention, String pesticideWeight
                                ){

        List<String> pesticideFiles = Arrays.asList( "pesticides/Golden Copper.png", "pesticides/Guang Py.jpg", "pesticides/Maxcin 2 SL.jpg", "pesticides/M CoCide 77%WP.jpg", "pesticides/M-Domail 72%WP.jpg", "pesticides/Mega Bay Shin.png", "pesticides/Mega Bishop.png", "pesticides/Mega Copper.png", "pesticides/Mega Cruiser.png", "pesticides/Mega Fenzo.png", "pesticides/M Hexa 5% EC.jpg", "pesticides/MLM Mancozeb 80% WP.jpg", "pesticides/M-Pro 25% EC.jpg", "pesticides/M-Style 25%SC.jpg", "pesticides/ကျူပရိုမက်စ် ၈၅ ဒဗလျူပီ.jpg", "pesticides/ကျူပရို-မိုင်စင် ၄၇၀ ဒဗလျူပီ.jpg", "pesticides/ကျူပရောက်ဆက်.jpg", "pesticides/ကာစင် ၂ အက်စ်အယ်.jpg", "pesticides/ကာဆူဂါ.jpg", "pesticides/ကာဗာ ၂၅ အက်စ်စီ.jpg", "pesticides/ဆိုင်မို-ဇက် ၇၂ ဒဗလျူပီ.jpg", "pesticides/ဇိုလာ ၂၅ အီးစီ.jpg", "pesticides/ဇီဖလို.jpg", "pesticides/တြိုင်-ဂိုးလ် ဒဗလျူပီ.jpg", "pesticides/ထရိုင်းဒန့်.jpg", "pesticides/နိုက်စ် ၅၂၅ အက်စ်အီး.jpg", "pesticides/ဖာတီဇို ၂၀ ဒဗလ်ဴပီ.jpg", "pesticides/ဘင်ဇိုကာ၅၅၀ အက်စ်စီ.jpg", "pesticides/ဘာလီဆိုဒ် ၅ အက်(စ်)အယ်(လ်).jpg", "pesticides/ဘီလီယံ.jpg", "pesticides/လင်းနစ် ၂၀ ဒဗလျူပီ.jpg", "pesticides/သာလွန် ၇၂ အက်စ်စီ.jpg", "pesticides/ဟေဗင်.jpg", "pesticides/ဩဘာစပါးမှိုဆေး.jpg" );
        var pesticideNames = Arrays.asList( "လင်းနစ် ၂၀ ဒဗလျူပီ", "ဇိုလာ ၂၅ အီးစီ", "ဘင်ဇိုကာ၅၅၀ အက်စ်စီ", "ကာစင် ၂ အက်စ်အယ်", "နိုက်စ် ၅၂၅ အက်စ်အီး", "ဘာလီဆိုဒ် ၅ အက်(စ်)အယ်(လ်)", "ဆိုင်မို-ဇက် ၇၂ ဒဗလျူပီ", "ကျူပရိုမက်စ် ၈၅ ဒဗလျူပီ", "ကာဗာ ၂၅ အက်စ်စီ", "ကျူပရို-မိုင်စင် ၄၇၀ ဒဗလျူပီ", "သာလွန် ၇၂ အက်စ်စီ", "တြိုင်-ဂိုးလ် ဒဗလျူပီ", "M Hexa 5% EC", "M CoCide 77%WP", "M-Domail 72%WP", "MLM Mancozeb 80% WP", "M-Style 25%SC", "M-Pro 25% EC", "ကာဆူဂါ", "ကျူပရောက်ဆက်", "ဘီလီယံ", "ထရိုင်းဒန့်", "ဩဘာစပါးမှိုဆေး", "ဟေဗင်", "ရီဗိုက်", "ဇီဖလို", "ပရိုဝမ်း", "Golden Copper", "Mega Bay Shin", "Mega Bishop", "Mega Copper", "Mega Cruiser", "Mega Fenzo", "Guang Py", "ဖာတီဇို ၂၀ ဒဗလ်ဴပီ", "Maxcin 2 SL");



        Disease disease = new Disease();
        disease.setName(diseaseName);


//        "imagePath": "pesticides/79b96221-a387-4378-a676-94cb850400d1_Screenshot from 2026-01-27 22-04-34.png"

        Virus virus = new Virus();
        virus.setName(virusName);

        Pesticide pesticide = new Pesticide();
        pesticide.setName(pesticideName);
        pesticide.setInfo(pesticideInfo);
        pesticide.setPrice(pesticidePrice);
        pesticide.setWeight(pesticideWeight);
        pesticide.setIngredients(pesticideIngredient);
        pesticide.setImagePath(pesticideImagePath);

        Cure cure = new Cure();
        cure.setDisease(disease);
        cure.setVirus(virus);
        cure.getPesticides().add(pesticide);
        cure.setPrevention(prevention);

        cureRepository.save(cure);


    }


}
