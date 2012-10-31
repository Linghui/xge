#!/usr/bin/perl -w

use strict;
use warnings;

use GCTXC;
use GJava;

my $SCENE_NAME_KEY  = 'scene_name';
my $SCREEN_WIDHT    = 'screen_width';
my $SCREEN_HEIGHT   = 'screen_height';

my $BACKGROUND_IMG_KEY  = 'background';
my $MOVEMENT_LIMIT_Y    = 'limit_y';

my $SKIN_KEY    =   'skin';
my $TEXT_BUTTONS_KEY    =   'text_buttons';
my $TEXT_KEY    =   'text';
my $X_KEY   =   'x';
my $Y_KEY   =   'y';

my $LABELS_KEY  =   'labels';
my $STYLE_KEY   =   'style';

my $IMAGE_BUTTONS_KEY   =   'image_buttons';
my $IMAGE_BUTTONS_DOWN_REGION_KEY   =   'down_region';
my $IMAGE_BUTTONS_UP_REGION_KEY     =   'up_region';

my $LOADING_BACKGROUND_KEY  =   'loading_backgroud';
my $LADING_BAR_KEY  =   'loading_bar';
my $BAR_TOP     =   'bar_top';
my $BAR_BOTTOM  =   'bar_bottom';
my $BAR_WIDTH   =   'bar_width';
my $BAR_HEIGHT  =   'bar_height';
my $BAR_X   =   'bar_x';
my $BAR_Y   =   'bar_y';

my $LEADING_ROLE_SRC    =   'leading_role_src';

my $NPCS_KEY    =   'npcs';
my $NPC_NAME    =   'npc_name';
my $NPC_SRC     =   'npc_src';


my $GCTXC = GCTXC->new();

my $scene_config = {
    $SCENE_NAME_KEY => 'TestScreen',
    $SCREEN_WIDHT   => 800,
    $SCREEN_HEIGHT  => 480,
    $LOADING_BACKGROUND_KEY =>  'data/40009002_pack1.png',
    $LADING_BAR_KEY =>  {
        $BAR_TOP    =>  'default-round',
        $BAR_BOTTOM =>  'default-round',
        $BAR_WIDTH  =>  '300',
        $BAR_HEIGHT =>  '50',
        $BAR_X      =>  '50',
        $BAR_Y      =>  '50',
        
    },
    
    $BACKGROUND_IMG_KEY =>'data/map/pack.atlas?map1',
    $MOVEMENT_LIMIT_Y   => 200,
    $SKIN_KEY   =>  'data/uiskin.json',
    
    $LABELS_KEY =>  {
        'l1'  =>{
            $TEXT_KEY     =>  'Lv.990',
            $STYLE_KEY    =>  'yellow',
            $X_KEY        =>  20,
            $Y_KEY        =>  450,
        }
    },
    
    $TEXT_BUTTONS_KEY   =>  {
        'btn1'  =>  {
            $TEXT_KEY   =>  '充值',
            $X_KEY  =>  100,
            $Y_KEY  =>  100,
            
        },
    },
    $IMAGE_BUTTONS_KEY  =>  {
        'qishu'  =>  {
            $X_KEY  =>  730,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'qishu',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'qishu',
        },
        'bag'  =>  {
            $X_KEY  =>  660,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'bag',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'bag',
        },
        'zhenfa'  =>  {
            $X_KEY  =>  590,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'zhenfa',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'zhenfa',
        },
        'yaoguai'  =>  {
            $X_KEY  =>  520,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'yaoguai',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'yaoguai',
        },
        'caishen'  =>  {
            $X_KEY  =>  730,
            $Y_KEY  =>  410,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'caishen',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'caishen',
        },
    },
    
    $LEADING_ROLE_SRC   =>  'data/30100001',
    
    $NPCS_KEY    =>  {
        'cuihua'  =>  {
            $NPC_NAME   =>  '翠花',
            $NPC_SRC    =>  'data/30100001',
            $X_KEY      =>  '350',
            $Y_KEY      =>  '240',
        },
        'taishan'  =>  {
            $NPC_NAME   =>  '泰山',
            $NPC_SRC    =>  'data/30100002',
            $X_KEY      =>  '450',
            $Y_KEY      =>  '240',
        },
        'kezhan'  =>  {
            $NPC_NAME   =>  '客栈',
            $NPC_SRC    =>  'data/30100003',
            $X_KEY      =>  '750',
            $Y_KEY      =>  '240',
        },
        'shanpao'  =>  {
            $NPC_NAME   =>  '山炮',
            $NPC_SRC    =>  'data/30100004',
            $X_KEY      =>  '950',
            $Y_KEY      =>  '240',
        },
        'biesan'  =>  {
            $NPC_NAME   =>  '瘪三',
            $NPC_SRC    =>  'data/30100005',
            $X_KEY      =>  '1100',
            $Y_KEY      =>  '240',
        },
        'dana'  =>  {
            $NPC_NAME   =>  '大拿',
            $NPC_SRC    =>  'data/30100006',
            $X_KEY      =>  '1300',
            $Y_KEY      =>  '240',
        },
        'dasheng'  =>  {
            $NPC_NAME   =>  '大圣',
            $NPC_SRC    =>  'data/30100008',
            $X_KEY      =>  '1450',
            $Y_KEY      =>  '240',
        },
        'zhu'  =>  {
            $NPC_NAME   =>  '猪',
            $NPC_SRC    =>  'data/30100009',
            $X_KEY      =>  '1800',
            $Y_KEY      =>  '180',
        },
    },
};

#my $config_config = $GCTXC->convert_to_java($scene_config);

my $gJava = GJava->new();
$gJava->generate_java($scene_config);
