#!/usr/bin/perl -w

use strict;
use warnings;

use GCTXC;
use GJava;
use JSON;
use Getopt::Std;

# 下面这些定义全都是例子 可供参考
my $SCENE_NAME_KEY  = 'scene_name';
my $SCREEN_WIDHT    = 'screen_width';
my $SCREEN_HEIGHT   = 'screen_height';

my $BACKGROUND_IMG_KEY  = 'background';
my $MOVEMENT_LIMIT_Y    = 'limit_y';

my $SKIN_KEY    =   'skin';
my $NAME        =   'name';
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

my $scene_config = {};

$scene_config = {
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
    $LEADING_ROLE_SRC   =>  'data/30100001',
    $SKIN_KEY   =>  'data/uiskin.json',
    
    $LABELS_KEY =>  [
        {
            $NAME       =>  'l1',
            $TEXT_KEY   =>  'Lv.990',
            $STYLE_KEY  =>  'yellow',
            $X_KEY      =>  20,
            $Y_KEY      =>  450,
        }
    ],
    
    $TEXT_BUTTONS_KEY   =>  [
        {
            $NAME   =>  'btn1',
            $TEXT_KEY   =>  '充值',
            $X_KEY  =>  80,
            $Y_KEY  =>  420,
            
        },
    ],
    
    $IMAGE_BUTTONS_KEY  =>  [
        {
            $NAME   =>  'qishu',
            $X_KEY  =>  730,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'qishu',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'qishu',
        },
        {
            $NAME   =>  'bag',
            $X_KEY  =>  660,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'bag',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'bag',
        },
        {
            $NAME   =>  'zhenfa',
            $X_KEY  =>  590,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'zhenfa',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'zhenfa',
        },
        {
            $NAME   =>  'yaoguai',
            $X_KEY  =>  520,
            $Y_KEY  =>  10,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'yaoguai',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'yaoguai',
        },
        {
            $NAME   =>  'caishen',
            $X_KEY  =>  730,
            $Y_KEY  =>  410,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  =>  'caishen',
            $IMAGE_BUTTONS_UP_REGION_KEY    =>  'caishen',
        },
    ],

    
    $NPCS_KEY    =>  [
        {
            $NAME   =>  'cuihua',
            $NPC_NAME   =>  '翠花',
            $NPC_SRC    =>  'data/30100001',
            $X_KEY      =>  '350',
            $Y_KEY      =>  '240',
        },
        {
            $NAME   =>  'taishan',
            $NPC_NAME   =>  '泰山',
            $NPC_SRC    =>  'data/30100002',
            $X_KEY      =>  '450',
            $Y_KEY      =>  '240',
        },
        {
            $NAME   =>  'kezhan',
            $NPC_NAME   =>  '客栈',
            $NPC_SRC    =>  'data/30100003',
            $X_KEY      =>  '750',
            $Y_KEY      =>  '240',
        },
        {
            $NAME   =>  'shanpao',
            $NPC_NAME   =>  '山炮',
            $NPC_SRC    =>  'data/30100004',
            $X_KEY      =>  '950',
            $Y_KEY      =>  '240',
        },
        {
            $NAME   =>  'biesan',
            $NPC_NAME   =>  '瘪三',
            $NPC_SRC    =>  'data/30100005',
            $X_KEY      =>  '1100',
            $Y_KEY      =>  '240',
        },
        {
            $NAME   =>  'dana',
            $NPC_NAME   =>  '大拿',
            $NPC_SRC    =>  'data/30100006',
            $X_KEY      =>  '1300',
            $Y_KEY      =>  '240',
        },
        {
            $NAME   =>  'dasheng',
            $NPC_NAME   =>  '大圣',
            $NPC_SRC    =>  'data/30100008',
            $X_KEY      =>  '1450',
            $Y_KEY      =>  '240',
        },
        {
            $NAME   =>  'zhu',
            $NPC_NAME   =>  '猪',
            $NPC_SRC    =>  'data/30100009',
            $X_KEY      =>  '1800',
            $Y_KEY      =>  '180',
        },
    ],
};

# 事例结束 ＝＝＝＝＝＝ END

#my $config_config = $GCTXC->convert_to_java($scene_config);

#my $json_text = to_json($scene_config);
#print $json_text;
#print "\n";

#use Data::Dumper qw/Dumper/;
#print Dumper($scene_config);

my $class_output_dir = 'classes/';

my %opts = ();

# options
# h: help
# f: for file or stdin input
# none: for command line string input
getopts('hf', \%opts);

if( $opts{h} ){
    &usage();
    exit 0;
}

my $input = undef;

if( $opts{f} ){
    $input = <>;
    chomp $input;
} else {
    
    if(!defined($ARGV[0])){
        print STDERR "Need json config string input\n";
        &usage();
        exit 1;
    }
    $input = $ARGV[0];
}


$scene_config = from_json($input);

my $gJava = GJava->new();
my $scene_class = $gJava->generate_scene($scene_config);

open WRT, "> $class_output_dir".$scene_config->{$SCENE_NAME_KEY}.".java"
    or die "open file error $!";

print WRT "$scene_class";

close WRT;
#print "$class";
# output sence class done


my $WINDOW_CLASS = 'window_class';
my $TITLE   = 'title';
# output window class
my $window_config = {
    $WINDOW_CLASS => 'TestWindow',
    $TITLE  => 'test',
};

my $window_class = $gJava->generate_window($window_config);
open WRT, "> $class_output_dir".$window_config->{$WINDOW_CLASS}.".java"
or die "open file error $!";

print WRT "$window_class";

close WRT;

sub usage(){

    print <<EOF;
Usage:
    perl $0 [-h | -f \$file_name/STDIN | \$json_string]
    -h: help
    -f: file or STDIN input mode
        example: perl $0 -f config_demo.json
    no options: for command json string input
    
EOF
}

sub HELP_MESSAGE(){
    &usage();
    exit 0;
}
