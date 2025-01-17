package org.phoenix.giftawayremake;

import org.phoenix.giftawayremake.command.BaseCommand;
import org.phoenix.giftawayremake.command.CommandHandler;
import org.phoenix.giftawayremake.command.subcommands.*;
import org.phoenix.giftawayremake.command.subcommands.startcommand.CustomMode;
import org.phoenix.giftawayremake.command.subcommands.startcommand.MMOMode;
import org.phoenix.giftawayremake.command.subcommands.startcommand.VanillaMode;
import org.phoenix.giftawayremake.command.tabcommand.TabComplete;
import org.phoenix.giftawayremake.eventhandler.BukkitRunnableEvent;
import org.phoenix.giftawayremake.eventhandler.ChatMuteEvent;
import org.phoenix.giftawayremake.eventhandler.PlayerDataEvent;
import org.phoenix.giftawayremake.eventhandler.UpdateNotifyEvent;
import org.phoenix.giftawayremake.filesmanager.LanguageDataFiles;
import org.phoenix.giftawayremake.filesmanager.PlayerDataFiles;
import org.phoenix.giftawayremake.filesmanager.SoundDataFiles;
import org.phoenix.giftawayremake.placeholderapi.PAPIExpansion;
import org.phoenix.giftawayremake.utils.ColorCodeTranslate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class GiftawayCore extends JavaPlugin implements Listener {

    public static GiftawayCore INSTANCE;
    private int ms;
    private String description = getDescription().getDescription();
    private String version = getDescription().getVersion();

    private File soundsConfigFile;
    private FileConfiguration soundsConfig;
    private File languagesConfigFile;
    private FileConfiguration languagesConfig;

    public Player rplayer;
    public HashSet<UUID> alreadyWin = new HashSet<>();
    public boolean alreadyCMD = false;
    public boolean muted = false;

    public void registerCommand() {
        CommandHandler handler = new CommandHandler();
        if (getConfig().getString("TypeMode").equals("Vanilla")) {
            handler.register("start", new VanillaMode(this));
        } else if (getConfig().getString("TypeMode").equals("MMO")) {
            handler.register("start", new MMOMode(this));
        } else if (getConfig().getString("TypeMode").equals("Custom")) {
            handler.register("start", new CustomMode(this));
        } else {
            getLogger().info(ColorCodeTranslate.chat("&cSorry! This Mode Type is not available in giftaway plugin. Check config.yml to check typo in TypeMode configuration."));
            Bukkit.getPluginManager().disablePlugin(this);
        }
        handler.register("giftaway", new BaseCommand(this));
        handler.register("help", new HelpCommand(this));
        handler.register("info", new InfoCommand(this));
        handler.register("check", new CheckCommand(this));
        handler.register("reload", new ReloadCommand(this));
        handler.register("version", new VersionCommand(this));
        getCommand("giftaway").setExecutor(handler);
    }

    @Override
    public void onEnable() {

        ms = 0;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ms++;
            }
        };
        timer.scheduleAtFixedRate(task, 1, 1);

        // This will check plugin hooked for PlaceholderAPI
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PAPIExpansion().register();
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info("Successfully hook into PlaceholderAPI");
        }

        // This will check plugin hooked for MMOItems
        if (Bukkit.getPluginManager().isPluginEnabled("MMOItems")) {
            getLogger().info("Successfully hook into MMOItems");
        } else {
            getLogger().info(ColorCodeTranslate.chat("Couldn't find MMOItems Plugin! Skipping dependency to load."));
        }

        // This will check plugin hooked for InteractiveChat
        if (Bukkit.getPluginManager().isPluginEnabled("InteractiveChat")) {
            getLogger().info("Successfully hook into InteractiveChat");
        } else {
            getLogger().info(ColorCodeTranslate.chat("Couldn't find InteractiveChat Plugin! Skipping dependency to load."));
        }

        // This will check version on config.yml
        if (!getConfig().getString("version").equals(version)) {
            throw new RuntimeException("Plugin version is corrupted on config.yml");
        }

        // Creating and load config.yml file
        File configFile = new File(getDataFolder() + File.separator + "config.yml");

        if (!configFile.exists()) {
            getConfig().addDefault("language", "en_US");
            getConfig().addDefault("prefix", "&7[&#1BFF00&lGiftaway&7] ");
            getConfig().addDefault("chatMute", true);
            getConfig().addDefault("TypeMode", "Vanilla");
            getConfig().addDefault("delay", 40);
            getConfig().addDefault("needPlayer", 3);
            getConfig().addDefault("updateNotify", true);
            getConfig().addDefault("version", version);
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        } else {
            saveDefaultConfig();
            reloadConfig();
        }

        // Register file onEnable method
        INSTANCE = this;
        this.registerCommand();
        this.getCommand("giftaway").setTabCompleter(new TabComplete(this));
        new PlayerDataEvent(this);
        new ChatMuteEvent(this);
        new UpdateNotifyEvent(this);

        // Setup sounds.yml Files
        SoundDataFiles.setupSoundFile();
        SoundDataFiles.getSoundConfig().options().header(
                "######################################################\n" +
                "                                                     #\n" +
                "        Giftaway Sounds Configuration Section        #\n" +
                "                                                     #\n" +
                "######################################################\n" +
                "                                                      \n" +
                "You can change sounds on giftaway system in here.     \n" +
                "Note: The sound must be valid from Minecraft Sounds.  \n" +
                "Sounds List: https://www.digminecraft.com/lists/sound_list_pc.php");
        if (!SoundDataFiles.getSoundConfig().isSet("sounds")) {
            SoundDataFiles.getSoundConfig().set("sounds.onLock", "BLOCK_AMETHYST_BLOCK_FALL");
            SoundDataFiles.getSoundConfig().set("sounds.onUnlock", "BLOCK_AMETHYST_BLOCK_FALL");
            SoundDataFiles.getSoundConfig().set("sounds.onStarting", "ENTITY_WITHER_SPAWN");
            SoundDataFiles.getSoundConfig().set("sounds.onSearching", "ENTITY_ENDER_DRAGON_FLAP");
            SoundDataFiles.getSoundConfig().set("sounds.onGetting", "ENTITY_ENDER_DRAGON_FLAP");
            SoundDataFiles.getSoundConfig().set("sounds.onWinner", "ENTITY_ENDER_DRAGON_GROWL");
        }
        SoundDataFiles.getSoundConfig().options().copyHeader(true);
        SoundDataFiles.getSoundConfig().options().copyDefaults(true);
        SoundDataFiles.soundSave();

        // Setup playerdata.yml Files
        PlayerDataFiles.setupPlayersFile();
        PlayerDataFiles.getPlayerConfig().options().header(
                "######################################################\n" +
                "                                                     #\n" +
                "     Giftaway Plugin Winner Players Data Files       #\n" +
                "                                                     #\n" +
                "######################################################\n" +
                "                                                      \n" +
                "PLEASE DON'T TOUCH THIS SECTION UNLESS YOU DON'T KNOW WHAT ARE YOU DOING!");
        if (!PlayerDataFiles.getPlayerConfig().isSet("WinnerData")) {
            PlayerDataFiles.getPlayerConfig().createSection("WinnerData");
        }
        PlayerDataFiles.getPlayerConfig().options().copyHeader(true);
        PlayerDataFiles.getPlayerConfig().options().copyDefaults(true);
        PlayerDataFiles.playerSave();

        // Setup Language Folder Files
        LanguageDataFiles.setupLanguageFolder(this);

        // Setup en_US.yml Files inside Language Folder
        LanguageDataFiles.setupLanguageUSFiles();
        LanguageDataFiles.getLanguageUSConfig().options().header(
                "######################################################\n" +
                "                                                     #\n" +
                "   Giftaway English Language Configuration Section   #\n" +
                "                                                     #\n" +
                "######################################################\n" +
                "                                                      \n" +
                "You can change all English Language on Giftaway in here.\n" +
                "Note: The Language must be valid format in below.     \n" +
                "Available Placeholder: %giftaway_name%, %giftaway_winner%, %player%, and %target%\n" +
                "                                                      \n" +
                "NOTE: YOU CAN CHANGE MESSAGE BUT YOU CAN'T CHANGE ANY CONFIGURATION SECTION\n" +
                "       ON LANGUAGE FILE UNLESS YOU DON'T KNOW WHAT ARE YOU DOING!");
        if (!LanguageDataFiles.getLanguageUSConfig().isSet("languages")) {
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayChat." + "chatLock", "&7&lChat has been lock by &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayChat." + "chatUnlock", "&7&lChat has been unlock by &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayChat." + "chatMutePerm", "&cSorry! You cant chat for now.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "searchingWinner", "&a&lSearching giftaway winner....");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "gettingWinner", "&a&lGetting giftaway winner....");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lHas started giftaway with items in the form of &b&l");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "winnerBroadcast", "&a&lCongrats! &e&l%giftaway_winner% &a&lGet a reward from a giftaway in the form of &b&l");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lHas started giftaway with MMO items in the form of &b&l");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "winnerBroadcast", "&a&lCongrats! &e&l%giftaway_winner% &a&lGet a MMO Items from a giftaway in the form of &b&l");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lHas started giftaway with items in the form of &b&l");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "winnerBroadcast", "&a&lCongrats! &e&l%giftaway_winner% &a&lGet a reward from a giftaway in the form of &b&l");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayReward." + "invalidReward", "&cUnknown material: ");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayReward." + "VanillaMode." + "noAmount", "&cPlease write you amount item to giftaway.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOIdItem", "&cPlease write your reward item you want to giftaway.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOAmount", "&cPlease write you amount item to giftaway.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOUnidentify", "&cPlease write your unidentify chance to MMO Item reward you want to giftaway.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawayReward." + "CustomMode." + "noReward", "&cPlease write your reward message you want to giftaway.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawaySystem." + "noPermission", "&cYou don''t have permission to use this command!");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawaySystem." + "alreadyCMD", "&cYou or other player already started giftaway! Please try again later.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawaySystem." + "invalidAmount", "&cYou must insert an integer to reward amount!");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftgiftawaySystem." + "successfulReload", "&aPlugin successfully reloaded.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawaySystem." + "unsuccessfulReload", "&cPlugin can''t reload properly! See console for more information.");
            LanguageDataFiles.getLanguageUSConfig().set("languages." + "giftawaySystem." + "requiredPlayer", "&cSorry! The required player of integer must be greater than 0. See console for more information.");
        }
        LanguageDataFiles.getLanguageUSConfig().options().copyHeader(true);
        LanguageDataFiles.getLanguageUSConfig().options().copyDefaults(true);
        LanguageDataFiles.languageUSSave();

        // Setup ind_ID.yml Files inside Language Folder
        LanguageDataFiles.setupLanguageIDFiles();
        LanguageDataFiles.getLanguageIDConfig().options().header(
                "######################################################\n" +
                        "                                                     #\n" +
                        "  Giftaway Indonesian Language Configuration Section #\n" +
                        "                                                     #\n" +
                        "######################################################\n" +
                        "                                                      \n" +
                        "You can change all Indonesian Language on Giftaway in here.\n" +
                        "Note: The Language must be valid format in below.     \n" +
                        "Available Placeholder: %giftaway_name%, %giftaway_winner%, %player%, and %target%\n" +
                        "                                                      \n" +
                        "NOTE: YOU CAN CHANGE MESSAGE BUT YOU CAN'T CHANGE ANY CONFIGURATION SECTION\n" +
                        "       ON LANGUAGE FILE UNLESS YOU DON'T KNOW WHAT ARE YOU DOING!");
        if (!LanguageDataFiles.getLanguageIDConfig().isSet("languages")) {
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayChat." + "chatLock", "&7&lChat telah dikunci oleh &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayChat." + "chatUnlock", "&7&lChat telah dibuka oleh &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayChat." + "chatMutePerm", "&cMaaf! Kamu tidak bisa berbicara untuk sekarang.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "searchingWinner", "&a&lMencari pemenang giftaway....");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "gettingWinner", "&a&lMendapatkan pemenang giftaway....");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lTelah memulai giftaway dengan barang berupa &b&l");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "winnerBroadcast", "&a&lCongrats! &e&l%giftaway_winner% &a&lMendapatkan hadiah dari giftaway berupa &b&l");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lTelah memulai giftaway dengan barang MMO berupa &b&l");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "winnerBroadcast", "&a&lSelamat! &e&l%giftaway_winner% &a&lMendapatkan barang MMO dari giftaway berupa &b&l");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lTelah memulai giftaway dengan barang berupa &b&l");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "winnerBroadcast", "&a&lSelamat! &e&l%giftaway_winner% &a&lMendapatkan hadiah dari giftaway berupa &b&l");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayReward." + "invalidReward", "&cMaterial tidak diketahui: ");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayReward." + "VanillaMode." + "noAmount", "&c&cTolong tulis jumlah barang yang ingin kamu giftaway.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOIdItem", "&c&cTolong tulis barang kamu yang ingin kamu giftaway.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOAmount", "&cTolong tulis jumlah barang yang ingin kamu giftaway.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOUnidentify", "&cTolong tulis kesempatan tak teridentifikasi ke barang MMO yang ingin kamu giftaway.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawayReward." + "CustomMode." + "noReward", "&cTolong tulis pesan hadiah kamu yang ingin kamu giftaway.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawaySystem." + "noPermission", "&cKamu tidak ada izin untuk menggunakan command ini!");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawaySystem." + "alreadyCMD", "&cKamu atau player lain telah memulai giftaway! Tolong coba kembali nanti.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawaySystem." + "invalidAmount", "&cKamu harus memasukan integer ke jumlah hadiah!");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawaySystem." + "successfulReload", "&aPlugin telah berhasil di muat ulang.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawaySystem." + "unsuccessfulReload", "&cPlugin tidak dapat dimuat dengan benar! Lihat console untuk informasi lebih lanjut.");
            LanguageDataFiles.getLanguageIDConfig().set("languages." + "giftawaySystem." + "requiredPlayer", "&cMaaf! Player yang dibutuhkan dari integer harus lebih besar dari pada 0. Lihat console untuk informasi lebih lanjut.");
        }
        LanguageDataFiles.getLanguageIDConfig().options().copyHeader(true);
        LanguageDataFiles.getLanguageIDConfig().options().copyDefaults(true);
        LanguageDataFiles.languageIDSave();

        // Setup rus_RU.yml Files inside Language Folder
        LanguageDataFiles.setupLanguageRUFiles();
        LanguageDataFiles.getLanguageRUConfig().options().header(
                "######################################################\n" +
                        "                                                     #\n" +
                        "    Giftaway Rusian Language Configuration Section   #\n" +
                        "                                                     #\n" +
                        "######################################################\n" +
                        "                                                      \n" +
                        "You can change all Rusian Language on Giftaway in here.\n" +
                        "Note: The Language must be valid format in below.     \n" +
                        "Available Placeholder: %giftaway_name%, %giftaway_winner%, %player%, and %target%\n" +
                        "                                                      \n" +
                        "NOTE: YOU CAN CHANGE MESSAGE BUT YOU CAN'T CHANGE ANY CONFIGURATION SECTION\n" +
                        "       ON LANGUAGE FILE UNLESS YOU DON'T KNOW WHAT ARE YOU DOING!");
        if (!LanguageDataFiles.getLanguageRUConfig().isSet("languages")) {
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayChat." + "chatLock", "&7&lЧат был заблокирован &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayChat." + "chatUnlock", "&7&lЧат был разблокирован &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayChat." + "chatMutePerm", "&cИзвини! Вы не можете общаться сейчас.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "searchingWinner", "&a&lИщем победителя розыгрыша подарков....");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "gettingWinner", "&a&lПолучение подарка победителю....");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lНачал раздачу подарков с предметами в виде &b&l");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "winnerBroadcast", "&a&lПоздравляю! &e&l%giftaway_winner% &a&lПолучите вознаграждение от подарка в виде &b&l");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lНачал розыгрыш ММО-предметов в виде &b&l");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "winnerBroadcast", "&a&lПоздравляю! &e&l%giftaway_winner% &a&lПолучите MMO-предметы в виде подарков в виде &b&l");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lНачал раздачу подарков с предметами в виде &b&l");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "winnerBroadcast", "&a&lПоздравляю! &e&l%giftaway_winner% &a&lПолучите вознаграждение от подарка в виде &b&l");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayReward." + "invalidReward", "&cНеизвестный материал: ");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayReward." + "VanillaMode." + "noAmount", "&cПожалуйста, напишите сумму товара в подарок.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOIdItem", "&cПожалуйста, напишите награду, которую вы хотите подарить.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOAmount", "&cПожалуйста, напишите сумму товара в подарок.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOUnidentify", "&cПожалуйста, напишите свой неопознанный шанс в награду за MMO-предмет, который вы хотите подарить.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawayReward." + "CustomMode." + "noReward", "&cПожалуйста, напишите сообщение о вознаграждении, которое вы хотите получить в подарок.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawaySystem." + "noPermission", "&cУ вас нет разрешения на использование этой команды!");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawaySystem." + "alreadyCMD", "&cВы уже начали розыгрыш подарков! Пожалуйста, повторите попытку позже.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawaySystem." + "invalidAmount", "&cВы должны вставить целое число для суммы вознаграждения!");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawaySystem." + "successfulReload", "&aПлагин успешно перезагружен.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawaySystem." + "unsuccessfulReload", "&cПлагин не может перезагрузиться должным образом! См. консоль для получения дополнительной информации.");
            LanguageDataFiles.getLanguageRUConfig().set("languages." + "giftawaySystem." + "requiredPlayer", "&cПрости! Требуемый игрок целого числа должен быть больше 0. Дополнительную информацию смотрите в консоли.");
        }
        LanguageDataFiles.getLanguageRUConfig().options().copyHeader(true);
        LanguageDataFiles.getLanguageRUConfig().options().copyDefaults(true);
        LanguageDataFiles.languageRUSave();

        // Setup ara_SA.yml Files inside Language Folder
        LanguageDataFiles.setupLanguageSAFiles();
        LanguageDataFiles.getLanguageSAConfig().options().header(
                "######################################################\n" +
                        "                                                     #\n" +
                        "   Giftaway Arabian Language Configuration Section   #\n" +
                        "                                                     #\n" +
                        "######################################################\n" +
                        "                                                      \n" +
                        "You can change all Arabian Language on Giftaway in here.\n" +
                        "Note: The Language must be valid format in below.     \n" +
                        "Available Placeholder: %giftaway_name%, %giftaway_winner%, %player%, and %target%\n" +
                        "                                                      \n" +
                        "NOTE: YOU CAN CHANGE MESSAGE BUT YOU CAN'T CHANGE ANY CONFIGURATION SECTION\n" +
                        "       ON LANGUAGE FILE UNLESS YOU DON'T KNOW WHAT ARE YOU DOING!");
        if (!LanguageDataFiles.getLanguageSAConfig().isSet("languages")) {
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayChat." + "chatLock", "&7&lتم قفل الدردشة بواسطة &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayChat." + "chatUnlock", "&7&lتم فتح الدردشة بواسطة &e&l%giftaway_name%");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayChat." + "chatMutePerm", ".آسف! لا يمكنك الدردشة الآنc&");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "searchingWinner", "&a&lالبحث عن الفائز الموهوب ....");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "gettingWinner", "&a&lالحصول على فائز هدية ....");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lبدأ الإهداء بعناصر على شكل &b&l");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "VanillaMode." + "winnerBroadcast", "&a&lتهانينا! &e&l%giftaway_winner% &a&lاحصل على مكافأة من هدية على شكل &b&l");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lبدأ الإهداء بعناصر MMO في شكل &b&l");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "MMOMode." + "winnerBroadcast", "&a&lتهانينا! &e&l%giftaway_winner% &a&lاحصل على عناصر MMO من giftaway في شكل &b&l");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "startedGiftaway", "&e&l%giftaway_name% &a&lبدأ الإهداء بعناصر على شكل &b&l");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayBroadcast." + "CustomMode." + "winnerBroadcast", "&a&lتهانينا! &e&l%giftaway_winner% &a&lاحصل على مكافأة من هدية على شكل &b&l");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayReward." + "invalidReward", "&cمادة غير معروفة: ");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayReward." + "VanillaMode." + "noAmount", "&cمن فضلك اكتب لك عنصر المبلغ إلى giftaway.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOIdItem", "&cيرجى كتابة عنصر المكافأة الذي تريد إهداءه.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOAmount", "&cمن فضلك اكتب لك عنصر المبلغ إلى giftaway.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayReward." + "MMOMode." + "noMMOUnidentify", "&cيرجى كتابة فرصتك غير المحددة لمكافأة عنصر MMO الذي تريد إهداءه.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawayReward." + "CustomMode." + "noReward", "&cيرجى كتابة رسالة المكافأة التي تريد إهدائها.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawaySystem." + "noPermission", "&cليس لديك إذن لاستخدام هذا الأمر!");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawaySystem." + "alreadyCMD", "&cلقد بدأت بالفعل Giftaway! الرجاء معاودة المحاولة في وقت لاحق.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawaySystem." + "invalidAmount", "&cيجب ادخال عدد صحيح لمكافأة المبلغ!");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawaySystem." + "successfulReload", "&aتمت إعادة تحميل البرنامج المساعد بنجاح.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawaySystem." + "unsuccessfulReload", "&cلا يمكن إعادة تحميل البرنامج المساعد بشكل صحيح! انظر وحدة التحكم لمزيد من المعلومات.");
            LanguageDataFiles.getLanguageSAConfig().set("languages." + "giftawaySystem." + "requiredPlayer", "&cآسف! يجب أن يكون لاعب العدد الصحيح المطلوب أكبر من 0. راجع وحدة التحكم لمزيد من المعلومات.");
        }
        LanguageDataFiles.getLanguageSAConfig().options().copyHeader(true);
        LanguageDataFiles.getLanguageSAConfig().options().copyDefaults(true);
        LanguageDataFiles.languageSASave();

        BukkitRunnableEvent bukkitRunnableEvent = new BukkitRunnableEvent(this);
        bukkitRunnableEvent.startRunnable();
        getLogger().info(ColorCodeTranslate.chat("&aPlugin Successfully Enable in " + ms + " ms!"));
    }

    @Override
    public void onDisable() {

        ms = 0;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ms++;
            }
        };
        timer.scheduleAtFixedRate(task, 1, 1);

        // Register file onDisable method
        INSTANCE = null;
        getLogger().info(ColorCodeTranslate.chat("&cPlugin Successfully Disable in " + ms + " ms!"));
        alreadyWin.clear();
    }

    // Getting getInstance static to return INSTANCE method
    public static GiftawayCore getInstance() {
        return INSTANCE;
    }

    // Getting getDesc String to return description of the plugin method
    public String getDesc() {
        return description;
    }

    // Getting getVersion String to return version of the plugin method
    public String getVersion() {
        return version;
    }
}
