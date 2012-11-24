#!/usr/bin/perl -w
# use for generate Java code
package GJava;

use strict;
use warnings;
use Data::Dumper qw/Dumper/;
use Template;

my $PACK_SUFFIX = '/pack';
my $TEMPLATE_DIR = 'templates';
my $TEMPLATE_DEFAULT_VERSION = '0.9.7';
my $TEMPLATE_PATH = undef;
my %SUPPORT_VERSION = (
            '96' => '0.9.6',
            '97' => '0.9.7',
            );

my $PACKAGE         = 'package';
my $IMPORT          = 'import';

my $CLASS_NAME      = 'class_name';
my $SCENE_NAME_KEY  = 'scene_name';
my $EXTENDS_KEY     = 'extends';
my $PARENT          = 'parent';
my $IMPLEMENTS_KEY  = 'implements';

my $SCREEN_WIDHT    = 'screen_width';
my $SCREEN_HEIGHT   = 'screen_height';

my $BACKGROUND_IMG_KEY  = 'background';
my $MOVEMENT_LIMIT_Y    = 'limit_y';

my $SKIN_KEY    =   'skin';
my $SOURCE_LOADING  = 'source_loading';
my $SOURCE_UNLOADING  = 'source_unloading';
my $INIT_KEY    =   'init';
my $INIT_BUTTONS    =   'init_buttons';

my $NAME    =   'name';
my $TEXT_BUTTONS_KEY    =   'text_buttons';
my $TEXT_KEY    =   'text';
my $X_KEY   =   'x';
my $Y_KEY   =   'y';
my $ADD_TO_KEY = 'add_to';

my $INIT_LABELS =   'init_labels';
my $LABELS_KEY  =   'labels';
my $STYLE_KEY   =   'style';

my $IMAGE_BUTTONS_KEY   =   'image_buttons';
my $IMAGE_BUTTONS_DOWN_REGION_KEY   =   'down_region';
my $IMAGE_BUTTONS_UP_REGION_KEY     =   'up_region';

my $LOADING_BACKGROUND_KEY  =   'loading_backgroud';

my $LADING_BAR_KEY  =   'loading_bar';
my $BAR_TOP =   'bar_top';
my $BAR_BOTTOM =   'bar_bottom';
my $BAR_WIDTH =   'bar_width';
my $BAR_HEIGHT =   'bar_height';
my $BAR_X   =   'bar_x';
my $BAR_Y   =   'bar_y';
my $BAR_SOURCE_COUNT   =   'bar_source_count';

my $LEADING_ROLE_SRC    =   'leading_role_src';
my $LEADING_ROLE_JSON   =   'leading_role_json';
my $LEADING_ROLE_PACK   =   'leading_role_pack';

my $INIT_NPCS   =   'init_npcs';
my $NPCS_KEY =   'npcs';
my $NPC_NAME    =   'npc_name';
my $NPC_SRC     =   'npc_src';

sub new(){
    my $class = shift;
    my $version = shift;
    
    my $self = {};
    
    if( defined($version) && exists $SUPPORT_VERSION{$version}){
        $TEMPLATE_PATH = "$TEMPLATE_DIR/".$SUPPORT_VERSION{$version}."/";
    } else {
        $TEMPLATE_PATH = "$TEMPLATE_DIR/$TEMPLATE_DEFAULT_VERSION/";
    }
    
    bless $self, $class;
    return $self;
}

sub generate_scene(){
    my $self = shift;
    my $config = shift;

#    print Dumper($config);
    
    # some useful options (see below for full list)
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/class.t";
    
    my $vars = {
        $CLASS_NAME  => $config->{$SCENE_NAME_KEY},
        $SCREEN_WIDHT  => $config->{$SCREEN_WIDHT},
        $SCREEN_HEIGHT  => $config->{$SCREEN_HEIGHT},
#        $BACKGROUND_IMG_KEY  => $config->{$BACKGROUND_IMG_KEY},
        $MOVEMENT_LIMIT_Y   =>  $config->{$MOVEMENT_LIMIT_Y},
        $LOADING_BACKGROUND_KEY  => $config->{$LOADING_BACKGROUND_KEY},
        $BAR_TOP  => $config->{$LADING_BAR_KEY}->{$BAR_TOP},
        $BAR_BOTTOM  => $config->{$LADING_BAR_KEY}->{$BAR_BOTTOM},
        $BAR_WIDTH  => $config->{$LADING_BAR_KEY}->{$BAR_WIDTH},
        $BAR_HEIGHT  => $config->{$LADING_BAR_KEY}->{$BAR_HEIGHT},
        $BAR_X  => $config->{$LADING_BAR_KEY}->{$BAR_X},
        $BAR_Y  => $config->{$LADING_BAR_KEY}->{$BAR_Y},
        $SKIN_KEY  => $config->{$SKIN_KEY},
        $PACKAGE    => 'package com.gol.game;',
    };

    if(defined($config->{$IMPLEMENTS_KEY})){
        $vars->{$IMPLEMENTS_KEY} = "$IMPLEMENTS_KEY ".$config->{$IMPLEMENTS_KEY};
    }
    
    if(defined($config->{$EXTENDS_KEY})){
        $vars->{$EXTENDS_KEY} = "$EXTENDS_KEY ".$config->{$EXTENDS_KEY};
    }
    
    # background
    $vars->{$BACKGROUND_IMG_KEY} = $self->get_background_resion($config->{$BACKGROUND_IMG_KEY});
    
    
    # loading and unloading source
#    my $source_loading_list = $config->{$SOURCE_LOADING};
    my $source_loading_list = $self->get_loading_list($config);
    my $all_src_load = "";
    my $all_src_unload = "";
    my $all_source_count = 0;
    for my $one (@$source_loading_list){
        my $load_line = $self->get_load_line($one);
        $all_src_load .= $load_line;
        my $unload_line = $self->get_unload_line($one);
        $all_src_unload .= $unload_line;
        $all_source_count++;
    }
    
    $vars->{$SOURCE_LOADING} = $all_src_load;
    $vars->{$SOURCE_UNLOADING} = $all_src_unload;
    $vars->{$BAR_SOURCE_COUNT}  = $all_source_count;
    
    my $init_labels_value  = $self->get_init_labels_value($config);
    $vars->{$INIT_LABELS} = $init_labels_value;
    
    my $init_buttons_value = $self->get_init_buttons_value($config);
    $vars->{$INIT_BUTTONS} = $init_buttons_value;

    if(defined($config->{$LEADING_ROLE_SRC})){
        
        # deal leading role resource
        my ($leading_role_src_id)  =   $config->{$LEADING_ROLE_SRC} =~ /(\d+)$/;
        
        $vars->{$LEADING_ROLE_JSON}   = $config->{$LEADING_ROLE_SRC} .'/'.$leading_role_src_id.'.json',
        $vars->{$LEADING_ROLE_PACK}   = $config->{$LEADING_ROLE_SRC} .$PACK_SUFFIX,
        $vars->{$PARENT}   = 'RPGScreen';
        
    } else {
        $vars->{$PARENT}   = 'TacticsScreen';
    }
    
    my $init_npcs_value =   $self->get_init_npcs_value($config);
    $vars->{$INIT_NPCS} = $init_npcs_value;
    
    my $output = undef;
    
    # process input template, substituting variables
    $template->process($input, $vars, \$output)
    || die $template->error();
    
    return $output;
    
}

sub get_background_resion(){
    
    my $self = shift;
    my $source = shift;
    
    my $line = "";
    
    if($source =~ /\.png/){
        $line = " new TextureRegion(manager.get(\"$source\", Texture.class));\n";
    } elsif ($source =~ /pack/){
        my ($pack_file, $region_name) = split /\?/, $source;
        $line = " manager.get(\"$pack_file\", TextureAtlas.class).findRegion(\"$region_name\");\n";
    }
    
    return $line;
}

sub get_load_line(){
    my $self = shift;
    my $source = shift;
    
    my $source_class;

    
    if($source =~ /\.png/){
        $source_class   = "Texture.class";
    } elsif ($source =~ /\.json/){
        $source_class   = "Skin.class";
    } elsif ($source =~ /\.fnt/){
        $source_class   = "BitmapFont.class";
    } elsif ($source =~ /pack/){
        $source_class   = "TextureAtlas.class";        
    }
    my $line = "        manager.load(\"$source\", $source_class);\n";    
    
    return $line;
}

sub get_unload_line(){
    my $self = shift;
    my $source = shift;
    
    my $line = "        manager.unload(\"$source\");\n";
    
    return $line;
}

sub get_loading_list(){
    my $self = shift;
    my $config = shift;
    my @list = ();

    push @list, $config->{$SKIN_KEY};
    
    my $background_resource = $config->{$BACKGROUND_IMG_KEY};
    if( $background_resource =~ /pack/ ){
        ($background_resource) = split /\?/, $background_resource;
    }
    push @list, $background_resource;
    
    if(defined($config->{$LEADING_ROLE_SRC})){
        push @list, $config->{$LEADING_ROLE_SRC}.$PACK_SUFFIX;
    }

    
    
    my $npcs = $config->{$NPCS_KEY};
    for my $one_npc (@$npcs){
        push @list, $one_npc->{$NPC_SRC}.$PACK_SUFFIX;
    }
    
    
    return \@list;
}

sub get_init_labels_value(){
    
    my $self = shift;
    my $config = shift;
    my $all_label_lines = "";
    
    my $LABEL_NAME  =   'label_name';
    my $LABEL_TEXT  =   'label_text';
    my $LABEL_STYLE =   'label_style';
    my $LABEL_X     =   'label_x';
    my $LABEL_Y     =   'label_y';
    
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/label.t";
    
    
    my $labels = $config->{$LABELS_KEY};
    for my $one_label (@$labels){
        
        my $vars = {
            $LABEL_NAME    =>   $one_label->{$NAME},
            $LABEL_TEXT    =>   $one_label->{$TEXT_KEY},
            $LABEL_STYLE    =>   $one_label->{$STYLE_KEY},
            $LABEL_X    =>   $one_label->{$X_KEY},
            $LABEL_Y    =>   $one_label->{$Y_KEY},
            $ADD_TO_KEY =>  'this.addActorBottom',
        };
        
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_label_lines .= $output;
        
    }
    
    return $all_label_lines;
}

sub get_init_buttons_value(){
    my $self = shift;
    my $config = shift;
    my $all_button_lines = "";
    
    my $BUTTON_NAME = 'button_name';
    my $BUTTON_TEXT = 'button_text';
    my $BUTTON_X = 'button_x';
    my $BUTTON_Y = 'button_y';
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/text_button.t";
    
    my $text_buttons = $config->{$TEXT_BUTTONS_KEY};
    for my $one_button (@$text_buttons){
        
        my $vars = {
            $BUTTON_NAME    =>   $one_button->{$NAME},
            $BUTTON_TEXT    =>   $one_button->{$TEXT_KEY},
            $BUTTON_X    =>   $one_button->{$X_KEY},
            $BUTTON_Y    =>   $one_button->{$Y_KEY},
            $ADD_TO_KEY =>  'this.addActorBottom',
        };
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_button_lines .= $output;
        
    }
    
    $input = "$TEMPLATE_PATH/image_button.t";
    
    my $image_buttons = $config->{$IMAGE_BUTTONS_KEY};
    for my $one_button (@$image_buttons){
        
        my $vars = {
            $BUTTON_NAME    =>   $one_button->{$NAME},
            $IMAGE_BUTTONS_DOWN_REGION_KEY  => 'null',
            $IMAGE_BUTTONS_UP_REGION_KEY  => 'null',
            $BUTTON_X    =>   $one_button->{$X_KEY},
            $BUTTON_Y    =>   $one_button->{$Y_KEY},
            $ADD_TO_KEY =>  'this.addActorBottom',
        };
        
        if(defined($one_button->{$IMAGE_BUTTONS_DOWN_REGION_KEY})){
            $vars->{$IMAGE_BUTTONS_DOWN_REGION_KEY} = 'skin.getRegion("'.$one_button->{$IMAGE_BUTTONS_DOWN_REGION_KEY}.'")';
        }
        
        if(defined($one_button->{$IMAGE_BUTTONS_UP_REGION_KEY})){
            $vars->{$IMAGE_BUTTONS_UP_REGION_KEY} = 'skin.getRegion("'.$one_button->{$IMAGE_BUTTONS_UP_REGION_KEY}.'")';
        }
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_button_lines .= $output;
        
    }
    
    
    return $all_button_lines;
}

sub get_init_npcs_value(){
    my $self    =   shift;
    my $config  =   shift;
    
    my $all_npc_lines = "";
    
    
    my $NPC_JSON = 'npc_json';
    my $NPC_PACK = 'npc_pack';
    my $NPC_X = 'npc_x';
    my $NPC_Y = 'npc_y';
    
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = "$TEMPLATE_PATH/npc.t";
    
    my $npcs = $config->{$NPCS_KEY};
    for my $one_npc (@$npcs){
        
        my ($src_id)  =   $one_npc->{$NPC_SRC} =~ /(\d+)$/;
#        print "src_id ====== $src_id\n";
        my $vars = {
            $NAME   =>   $one_npc->{$NAME}.'NpcActor',
            $NPC_JSON   => $one_npc->{$NPC_SRC}.'/'.$src_id.'.json',
            $NPC_PACK   => $one_npc->{$NPC_SRC}.$PACK_SUFFIX,
            $NPC_X   =>   $one_npc->{$X_KEY},
            $NPC_Y   =>   $one_npc->{$Y_KEY},
            $ADD_TO_KEY =>  'this.addActorBackground',
        };
        
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_npc_lines .= $output;
        
    }
    
    return $all_npc_lines;
}

sub generate_window(){
    my $self = shift;
    my $window_config = shift;
    
}

1


