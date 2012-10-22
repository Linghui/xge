#!/usr/bin/perl -w
# use for generate Java code
package GJava;

use strict;
use warnings;
use Data::Dumper qw/Dumper/;
use Template;

my $CLASS_NAME      = 'class_name';
my $SCENE_NAME_KEY  = 'scene_name';
my $EXTENDS_KEY     = 'extends';
my $IMPLEMENTS_KEY  = 'implements';

my $BACKGROUND_IMG_KEY  = 'background';
my $SKIN_KEY    =   'skin';
my $SOURCE_LOADING  = 'source_loading';
my $SOURCE_UNLOADING  = 'source_unloading';
my $INIT_KEY    =   'init';
my $INIT_BUTTONS    =   'init_buttons';

my $TEXT_BUTTONS_KEY    =   'text_buttons';
my $TEXT_KEY    =   'text';
my $X_KEY   =   'x';
my $Y_KEY   =   'y';

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
    my $self = {};
    bless $self, $class;
    return $self;
}

sub generate_java(){
    my $self = shift;
    my $config = shift;

    print Dumper($config);
    
    # some useful options (see below for full list)
    my $t_config = {
        INTERPOLATE  => 1,               # expand "$var" in plain text
    };
    
    my $template = Template->new($t_config);
    
    my $input = 'templates/class.t';
    
    my $vars = {
        $CLASS_NAME  => $config->{$SCENE_NAME_KEY},
        $BACKGROUND_IMG_KEY  => $config->{$BACKGROUND_IMG_KEY},
        $LOADING_BACKGROUND_KEY  => $config->{$LOADING_BACKGROUND_KEY},
        $BAR_TOP  => $config->{$LADING_BAR_KEY}->{$BAR_TOP},
        $BAR_BOTTOM  => $config->{$LADING_BAR_KEY}->{$BAR_BOTTOM},
        $BAR_WIDTH  => $config->{$LADING_BAR_KEY}->{$BAR_WIDTH},
        $BAR_HEIGHT  => $config->{$LADING_BAR_KEY}->{$BAR_HEIGHT},
        $BAR_X  => $config->{$LADING_BAR_KEY}->{$BAR_X},
        $BAR_Y  => $config->{$LADING_BAR_KEY}->{$BAR_Y},
        $SKIN_KEY  => $config->{$SKIN_KEY},
    };
    
    if(defined($config->{$IMPLEMENTS_KEY})){
        $vars->{$IMPLEMENTS_KEY} = "$IMPLEMENTS_KEY ".$config->{$IMPLEMENTS_KEY};
    }
    
    if(defined($config->{$EXTENDS_KEY})){
        $vars->{$EXTENDS_KEY} = "$EXTENDS_KEY ".$config->{$EXTENDS_KEY};
    }
    
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
    
    my $init_buttons_value = $self->get_init_buttons_value($config);
    $vars->{$INIT_BUTTONS} = $init_buttons_value;

    my ($leading_role_src_id)  =   $config->{$LEADING_ROLE_SRC} =~ /(\d+)$/;
    
    $vars->{$LEADING_ROLE_JSON}   = $config->{$LEADING_ROLE_SRC} .'/'.$leading_role_src_id.'.json',
    $vars->{$LEADING_ROLE_PACK}   = $config->{$LEADING_ROLE_SRC} .'/pack',
    
    my $init_npcs_value =   $self->get_init_npcs_value($config);
    $vars->{$INIT_NPCS} = $init_npcs_value;
    
    # process input template, substituting variables
    $template->process($input, $vars)
    || die $template->error();
    
    
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
    push @list, $config->{$BACKGROUND_IMG_KEY};
    push @list, $config->{$LEADING_ROLE_SRC}.'/pack';
    
    
    my $npcs = $config->{$NPCS_KEY};
    for my $one_npc (keys %$npcs){
        push @list, $npcs->{$one_npc}->{$NPC_SRC}.'/pack';
    }
    
    
    return \@list;
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
    
    my $input = 'templates/text_button.t';
    
    my $text_buttons = $config->{$TEXT_BUTTONS_KEY};
    for my $one_button (keys %$text_buttons){
        
        my $vars = {
            $BUTTON_NAME    =>   $one_button,
            $BUTTON_TEXT    =>   $text_buttons->{$one_button}->{$TEXT_KEY},
            $BUTTON_X    =>   $text_buttons->{$one_button}->{$X_KEY},
            $BUTTON_Y    =>   $text_buttons->{$one_button}->{$Y_KEY},
            
        };
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_button_lines .= $output;
        
    }
    
    $input = 'templates/image_button.t';
    
    my $image_buttons = $config->{$IMAGE_BUTTONS_KEY};
    for my $one_button (keys %$image_buttons){
        
        my $vars = {
            $BUTTON_NAME    =>   $one_button,
            $IMAGE_BUTTONS_DOWN_REGION_KEY  => 'null',
            $IMAGE_BUTTONS_UP_REGION_KEY  => 'null',
            $BUTTON_X    =>   $image_buttons->{$one_button}->{$X_KEY},
            $BUTTON_Y    =>   $image_buttons->{$one_button}->{$Y_KEY},
            
        };
        
        if(defined($image_buttons->{$one_button}->{$IMAGE_BUTTONS_DOWN_REGION_KEY})){
            $vars->{$IMAGE_BUTTONS_DOWN_REGION_KEY} = 'skin.getRegion("'.$image_buttons->{$one_button}->{$IMAGE_BUTTONS_DOWN_REGION_KEY}.'")';
        }
        
        if(defined($image_buttons->{$one_button}->{$IMAGE_BUTTONS_UP_REGION_KEY})){
            $vars->{$IMAGE_BUTTONS_UP_REGION_KEY} = 'skin.getRegion("'.$image_buttons->{$one_button}->{$IMAGE_BUTTONS_UP_REGION_KEY}.'")';
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
    
    my $input = 'templates/npc.t';
    
    my $npcs = $config->{$NPCS_KEY};
    for my $one_npc (keys %$npcs){
        
        my ($src_id)  =   $npcs->{$one_npc}->{$NPC_SRC} =~ /(\d+)$/;
        print "src_id ====== $src_id\n";
        my $vars = {
            $NPC_NAME   =>   $one_npc.'NpcActor',
            $NPC_JSON   => $npcs->{$one_npc}->{$NPC_SRC}.'/'.$src_id.'.json',
            $NPC_PACK   => $npcs->{$one_npc}->{$NPC_SRC}.'/pack',
            $NPC_X   =>   $npcs->{$one_npc}->{$X_KEY},
            $NPC_Y   =>   $npcs->{$one_npc}->{$Y_KEY},
            
        };
        
        
        my $output;
        
        $template->process($input, $vars, \$output)
        || die $template->error();
        $all_npc_lines .= $output;
        
    }
    
    return $all_npc_lines;
}

1



